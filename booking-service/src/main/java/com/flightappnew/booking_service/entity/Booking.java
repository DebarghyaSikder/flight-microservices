package com.flightappnew.booking_service.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "bookings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    private String id;

    private String bookingReference;

    private String passengerName;
    private String passengerEmail;

    private String flightNumber;
    private String fromPlace;
    private String toPlace;
    private LocalDate journeyDate;

    private int seatsBooked;
    private BigDecimal totalPrice;

    private String status;          
    private LocalDateTime createdAt;
}
