package com.flightappnew.flight_service.controller;
import com.flightappnew.flight_service.dto.FlightRequest;
import com.flightappnew.flight_service.dto.FlightResponse;
import com.flightappnew.flight_service.dto.FlightSearchRequest;
import com.flightappnew.flight_service.service.FlightService;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // POST /flights – add flight
    // GET /flights – list flights

    @GetMapping("/availability")
    public Mono<Boolean> checkAvailability(@RequestParam String flightNumber,
                                           @RequestParam String journeyDate,   
                                           @RequestParam int seats) {
        LocalDate date = LocalDate.parse(journeyDate);
        return flightService.checkAvailability(flightNumber, date, seats);
    }
}