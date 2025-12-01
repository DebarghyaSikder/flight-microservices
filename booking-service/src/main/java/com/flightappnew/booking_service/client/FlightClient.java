package com.flightappnew.booking_service.client;

import java.time.LocalDate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "flight-service")
public interface FlightClient {

    @GetMapping("/flights/check-availability")
    boolean checkAvailability(@RequestParam String flightNumber,
                              @RequestParam
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                              LocalDate journeyDate,
                              @RequestParam int seats);
}
