package com.flightappnew.flight_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FlightRequest {

    @NotBlank
    private String airlineName;

    @NotBlank
    private String flightNumber;

    @NotBlank
    private String fromPlace;

    @NotBlank
    private String toPlace;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime departureTime;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime arrivalTime;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @Min(1)
    private int totalSeats;
}
