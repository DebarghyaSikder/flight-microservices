package com.flightappnew.booking_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

import lombok.Data;

@Data
public class BookingRequest {

    @NotBlank
    private String passengerName;

    @Email
    @NotBlank
    private String passengerEmail;

    @NotBlank
    private String flightNumber;

    @NotBlank
    private String fromPlace;

    @NotBlank
    private String toPlace;

    @NotNull
    @FutureOrPresent
    private LocalDate journeyDate;

    @Min(1)
    private int seats;

    @NotNull
    @DecimalMin("1.0")
    private BigDecimal pricePerSeat;
}
