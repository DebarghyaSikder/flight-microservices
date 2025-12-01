package com.flightappnew.booking_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BookingRequest {

    @NotBlank
    private String passengerName;

    @NotBlank
    @Email
    private String passengerEmail;

    @NotBlank
    private String flightNumber;

    @NotBlank
    private String fromPlace;

    @NotBlank
    private String toPlace;

    @NotNull
    private LocalDate journeyDate;

    @Min(1)
    private int seats;

    @NotNull
    private BigDecimal pricePerSeat;
}
