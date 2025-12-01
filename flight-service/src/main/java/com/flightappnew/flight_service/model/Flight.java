package com.flightappnew.flight_service.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "flights")
public class Flight {

    @Id
    private String id;

    private String airlineName;
    private String flightNumber;

    private String fromPlace;
    private String toPlace;

    private LocalDate departureDate;
    private LocalTime departureTime;

    private Integer totalSeats;
    private Integer availableSeats;

    private BigDecimal ticketPrice;
}