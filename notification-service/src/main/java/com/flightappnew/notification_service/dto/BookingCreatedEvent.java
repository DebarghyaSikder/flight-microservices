package com.flightappnew.notification_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingCreatedEvent {

    private String bookingId;
    private String bookingReference;
    private String passengerName;
    private String passengerEmail;

    private String flightNumber;
    private String fromPlace;
    private String toPlace;
    private LocalDate journeyDate;

    private Integer seatsBooked;
    private BigDecimal totalPrice;
}
