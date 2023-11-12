package com.pastbin.authentication;

import com.pastbin.authentication.dto.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 Отче наш, сущий на небесах!
 Да святится имя Твое;
 Да приидет Царствие Твое;
 Да будет воля Твоя и на земле, как на небе;
 Хлеб наш насущный дай нам на сей день;
 И прости нам долги наши, как и мы прощаем должникам нашим;
 И не введи нас в искушение, но избавь нас от лукавого.
 Ибо Твое есть Царство и сила и слава вовеки.
 Аминь.
 */
@EnableConfigurationProperties({RsaKeyProperties.class})
@SpringBootApplication
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

}
