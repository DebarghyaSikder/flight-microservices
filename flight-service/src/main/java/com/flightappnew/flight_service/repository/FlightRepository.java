package com.flightappnew.flight_service.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.flightappnew.flight_service.model.Flight;

import reactor.core.publisher.Flux;

public interface FlightRepository extends ReactiveMongoRepository<Flight, String> {

    Flux<Flight> findByFromPlaceAndToPlaceAndDepartureDateAndActiveTrue(
            String fromPlace,
            String toPlace,
            LocalDate departureDate
    );
}