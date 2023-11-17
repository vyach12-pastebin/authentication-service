package com.pastebin.authentication.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class VerificationCodeListenerConfiguration {
    @Value("${spring.rabbitmq.exchanges.register-notification}")
    private String internalExchange;

    @Value("${spring.rabbitmq.queues.register-notification}")
    private String transactionQueue;

    @Value("${spring.rabbitmq.routing-keys.register-notification}")
    private String internalTransactionRoutingKey;

    @Bean
    public TopicExchange transactionInternalTopicExchange() {
        return new TopicExchange(internalExchange);
    }

    @Bean
    public Queue transactionQueue() {
        return new Queue(transactionQueue);
    }

    @Bean
    public Binding internalToTransactionBinding() {
        return BindingBuilder
                .bind(transactionQueue())
                .to(transactionInternalTopicExchange())
                .with(internalTransactionRoutingKey);
    }
}
