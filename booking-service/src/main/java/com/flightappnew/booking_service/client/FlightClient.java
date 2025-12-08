package com.flightappnew.booking_service.client;

import java.time.LocalDate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "FLIGHT-SERVICE")
public interface FlightClient {

    @GetMapping("/flights/availability")
    Boolean checkAvailability(
            @RequestParam("flightNumber") String flightNumber,
            @RequestParam("departureDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate departureDate,
            @RequestParam("seats") int seats
    );
}
	