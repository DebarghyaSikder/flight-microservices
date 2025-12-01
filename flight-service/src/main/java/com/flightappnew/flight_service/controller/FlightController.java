package com.flightappnew.flight_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.flightappnew.flight_service.dto.FlightSearchRequest;
import com.flightappnew.flight_service.dto.FlightSearchResponse;
import com.flightappnew.flight_service.model.Flight;
import com.flightappnew.flight_service.service.FlightService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Flight> addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<FlightSearchResponse> searchFlights(@RequestBody FlightSearchRequest request) {
        return flightService.searchFlights(request);
    }
}