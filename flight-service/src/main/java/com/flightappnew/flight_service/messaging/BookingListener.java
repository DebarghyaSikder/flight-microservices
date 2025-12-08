package com.flightappnew.flight_service.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.flightappnew.flight_service.entity.Flight;
import com.flightappnew.flight_service.repository.FlightRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookingListener {

    private final FlightRepository flightRepository;

    @RabbitListener(queues = "booking.queue")
    public void consumeBooking(BookingMessage message) {

        Flight flight = flightRepository
                .findByFlightNumberAndDepartureDate(
                        message.getFlightNumber(),
                        message.getJourneyDate()
                )
                .block();  // blocking here is ok because @RabbitListener is synchronous

        if (flight != null) {
            int updated = flight.getAvailableSeats() - message.getSeatsBooked();
            flight.setAvailableSeats(updated);
            flightRepository.save(flight).subscribe(); // reactive save
            System.out.println("Updated seats for flight: " + message.getFlightNumber()
                    + " | Remaining: " + updated);
        } else {
            System.out.println("No flight found for booking message: " + message.getFlightNumber());
        }
    }
}
