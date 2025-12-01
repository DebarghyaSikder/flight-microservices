package com.flightappnew.flight_service.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime departureTime;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime arrivalTime;

    @NotNull
    @Min(0)
    private BigDecimal price;

    @Min(0)
    private int totalSeats;

    @Min(0)
    private int availableSeats;

    private boolean active;
}