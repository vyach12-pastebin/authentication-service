package com.finracy.authentication.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collections;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private Integer port;
/*
    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;*/

    @Value("${spring.data.mongodb.database}")
    private String targetDatabase;

    @Bean
    public MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost(host);
        mongo.setPort(port);
        return mongo;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(
                        builder -> builder.hosts(Collections.singletonList(new ServerAddress(host, port))))
                .build();

        return new MongoTemplate(MongoClients.create(settings), targetDatabase);
    }
}
