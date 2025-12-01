package com.flightappnew.flight_service.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.flightappnew.flight_service.entity.Flight;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightRepository extends ReactiveMongoRepository<Flight, String> {

    Flux<Flight> findByFromPlaceIgnoreCaseAndToPlaceIgnoreCaseAndDepartureDateAndActiveIsTrue(
            String fromPlace,
            String toPlace,
            LocalDate departureDate
    );

    Mono<Flight> findByFlightNumber(String flightNumber);
}