package com.flightappnew.flight_service.messaging;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookingMessage {
    private String flightNumber;
    private LocalDate journeyDate;
    private int seatsBooked;
}
