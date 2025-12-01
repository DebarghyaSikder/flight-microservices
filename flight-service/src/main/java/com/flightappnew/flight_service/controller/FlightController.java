package com.flightappnew.flight_service.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.flightappnew.flight_service.model.Flight;
import com.flightappnew.flight_service.service.FlightService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // Add new flight (inventory)
    @PostMapping
    public Mono<Flight> addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    // Search flights
    @GetMapping("/search")
    public Flux<Flight> searchFlights(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        return flightService.searchFlights(from, to, date);
    }

    // Just for checking everything is ok
    @GetMapping
    public Flux<Flight> findAll() {
        return flightService.findAll();
    }
}