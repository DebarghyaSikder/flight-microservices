package com.flightappnew.flight_service.controller;

import com.flightappnew.flight_service.dto.FlightRequest;
import com.flightappnew.flight_service.dto.FlightResponse;
import com.flightappnew.flight_service.dto.FlightSearchRequest;
import com.flightappnew.flight_service.entity.Flight;
import com.flightappnew.flight_service.service.FlightService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    // ---------- 1. Add a new flight ----------
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FlightResponse> createFlight(@Valid @RequestBody FlightRequest request) {
        return flightService.createFlight(request);
    }

    
    @PostMapping("/add")
    public ResponseEntity<Flight> addFlight(
            @RequestHeader("X-ROLE") String role,
            @RequestBody Flight flight) {

        if (!"ADMIN".equals(role)) {
            throw new RuntimeException("Access Denied");
        }

        return ResponseEntity.ok(flightService.addInventory(flight));
    }

    
    // ---------- 2. Search flights ----------
    @PostMapping("/search")
    public Flux<FlightResponse> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        return flightService.searchFlights(request);
    }

    // ---------- 3. Get by id ----------
    @GetMapping("/{id}")
    public Mono<ResponseEntity<FlightResponse>> getById(@PathVariable String id) {
        return flightService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // ---------- 4. Check seat availability (for Booking service / Feign) ----------
    @GetMapping("/availability")
    public Mono<Boolean> checkAvailability(
            @RequestParam String flightNumber,
            @RequestParam("departureDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam int seats
    ) {
        return flightService.checkAvailability(flightNumber, departureDate, seats);
    }
}
