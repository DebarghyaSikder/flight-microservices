package com.flightappnew.flight_service.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "flights")
public class Flight {

    @Id
    private String id;

    @NotBlank
    private String airlineName;

    @NotBlank
    private String flightNumber;

    @NotBlank
    private String fromPlace;

    @NotBlank
    private String toPlace;

    @NotNull
    private LocalDate departureDate;

    @NotNull
    private LocalTime departureTime;

    @NotNull
    private LocalDate arrivalDate;

    @NotNull
    private LocalTime arrivalTime;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @Min(0)
    private int totalSeats;

    @Min(0)
    private int availableSeats;

    private boolean active;
}
