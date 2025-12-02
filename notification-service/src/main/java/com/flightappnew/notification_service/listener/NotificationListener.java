package com.flightappnew.notification_service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.flightappnew.notification_service.dto.BookingCreatedEvent;

@Service
public class NotificationListener {

    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleBookingCreated(BookingCreatedEvent event) {

        log.info("Received booking event for notification: {}", event);

        // Simulate sending email
        String message = String.format(
                "Hello %s,\n\nYour booking %s for flight %s (%s ➝ %s) on %s has been confirmed.\n" +
                "Seats: %d\nTotal Amount: %s\n\nThank you for flying with us!",
                event.getPassengerName(),
                event.getBookingReference(),
                event.getFlightNumber(),
                event.getFromPlace(),
                event.getToPlace(),
                event.getJourneyDate(),
                event.getSeatsBooked(),
                event.getTotalPrice()
        );

        log.info("✉️ Sending email to {}:\n{}", event.getPassengerEmail(), message);
    }
}