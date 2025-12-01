package com.flightappnew.flight_service.controller;
import com.flightappnew.flight_service.dto.FlightRequest;
import com.flightappnew.flight_service.dto.FlightResponse;
import com.flightappnew.flight_service.dto.FlightSearchRequest;
import com.flightappnew.flight_service.service.FlightService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    /**
     * Create a new flight.
     * POST /flights
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FlightResponse> createFlight(@Valid @RequestBody FlightRequest request) {
        return flightService.createFlight(request);
    }

    /**
     * Search flights by fromPlace, toPlace, departureDate.
     * POST /flights/search
     */
    @PostMapping(value = "/search",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<FlightResponse> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        return flightService.searchFlights(request);
    }

    /**
     * Get single flight by id.
     * GET /flights/{id}
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<FlightResponse> getFlight(@PathVariable String id) {
        return flightService.getById(id);
    }
}