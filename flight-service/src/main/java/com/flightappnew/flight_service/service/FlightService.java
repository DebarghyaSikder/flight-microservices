package com.flightappnew.flight_service.service;


import com.flightappnew.flight_service.dto.FlightSearchRequest;
import com.flightappnew.flight_service.dto.FlightSearchResponse;
import com.flightappnew.flight_service.model.Flight;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightService {

    Mono<Flight> addFlight(Flight flight);

    Flux<FlightSearchResponse> searchFlights(FlightSearchRequest request);
}