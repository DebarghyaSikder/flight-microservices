package com.flightappnew.flight_service.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FlightSearchRequest {

    @NotBlank
    private String fromPlace;

    @NotBlank
    private String toPlace;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;
}