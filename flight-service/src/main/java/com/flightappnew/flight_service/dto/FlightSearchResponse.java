package com.flightappnew.flight_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightSearchResponse {

    private String id;
    private String airlineName;
    private String flightNumber;

    private String fromPlace;
    private String toPlace;

    private LocalDate departureDate;
    private LocalTime departureTime;

    private LocalDate arrivalDate;
    private LocalTime arrivalTime;

    private BigDecimal price;
    private Integer availableSeats;
}