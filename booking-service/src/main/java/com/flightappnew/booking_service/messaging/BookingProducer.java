package com.flightappnew.booking_service.messaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import com.flightappnew.booking_service.entity.Booking;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookingProducer {

    private final AmqpTemplate amqpTemplate;

    public void sendBookingMessage(Booking booking) {
        amqpTemplate.convertAndSend(
                "booking.exchange",
                "booking.key",
                booking
        );
        System.out.println("Booking message sent to RabbitMQ: " + booking.getBookingReference());
    }
}
