package com.flightappnew.booking_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "FLIGHT-SERVICE")   // must match Eureka app name
public interface FlightClient {

    @GetMapping("/flights/availability")
    Boolean checkAvailability(@RequestParam String flightNumber,
                              @RequestParam String journeyDate,
                              @RequestParam int seats);
}