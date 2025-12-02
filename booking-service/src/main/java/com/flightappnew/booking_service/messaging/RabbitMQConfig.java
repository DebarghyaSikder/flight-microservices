package com.flightappnew.booking_service.messaging;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange bookingExchange() {
        return new TopicExchange("booking.exchange");
    }

    @Bean
    public Queue bookingQueue() {
        return new Queue("booking.queue");
    }

    @Bean
    public Binding bookingBinding() {
        return BindingBuilder
                .bind(bookingQueue())
                .to(bookingExchange())
                .with("booking.key");
    }
}
