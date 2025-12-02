package com.flightappnew.notification_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange}")
    private String bookingExchange;

    @Value("${rabbitmq.queue}")
    private String bookingQueue;

    @Value("${rabbitmq.routing-key}")
    private String bookingRoutingKey;

    @Bean
    public DirectExchange bookingExchange() {
        return new DirectExchange(bookingExchange);
    }

    @Bean
    public Queue bookingQueue() {
        return new Queue(bookingQueue, true); // durable
    }

    @Bean
    public Binding bookingBinding() {
        return BindingBuilder
                .bind(bookingQueue())
                .to(bookingExchange())
                .with(bookingRoutingKey);
    }
}