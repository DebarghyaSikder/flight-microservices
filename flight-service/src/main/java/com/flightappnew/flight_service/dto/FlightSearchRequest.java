package com.flightappnew.flight_service.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FlightSearchRequest {

    private String fromPlace;
    private String toPlace;
    private LocalDate departureDate;
}
