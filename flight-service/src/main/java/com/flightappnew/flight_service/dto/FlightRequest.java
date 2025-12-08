package com.flightappnew.flight_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;
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

    // user sends "10:30"
    @NotBlank
    private String departureTime;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    @NotBlank
    private String arrivalTime;  // user sends "12:45"

    @DecimalMin("0.0")
    @NotNull
    private BigDecimal price;

    @Min(0)
    private int totalSeats;

    @Min(0)
    private int availableSeats;

    private boolean active;
}
