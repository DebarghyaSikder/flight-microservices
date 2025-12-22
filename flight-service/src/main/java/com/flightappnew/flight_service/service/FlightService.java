package com.flightappnew.flight_service.service;

import java.time.LocalDate;

import com.flightappnew.flight_service.dto.FlightRequest;
import com.flightappnew.flight_service.dto.FlightResponse;
import com.flightappnew.flight_service.dto.FlightSearchRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightService {

    Mono<FlightResponse> createFlight(FlightRequest request);

    Flux<FlightResponse> searchFlights(FlightSearchRequest request);

    Mono<FlightResponse> getById(String id);

    Mono<Boolean> checkAvailability(String flightNumber, LocalDate departureDate, int seats);
}
