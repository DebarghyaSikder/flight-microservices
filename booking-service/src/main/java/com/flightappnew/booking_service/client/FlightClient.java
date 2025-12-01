package com.flightappnew.booking_service.client;

import java.time.LocalDate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "FLIGHT-SERVICE", path = "/flights")
public interface FlightClient {

    @GetMapping("/check-availability")
    Boolean checkAvailability(
            @RequestParam("flightNumber") String flightNumber,
            @RequestParam("journeyDate") LocalDate journeyDate,
            @RequestParam("seats") int seats);
}
