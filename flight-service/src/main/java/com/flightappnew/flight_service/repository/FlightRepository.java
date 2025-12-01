package com.flightappnew.flight_service.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.flightappnew.flight_service.entity.Flight;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightRepository extends ReactiveMongoRepository<Flight, String> {

    // existing search method
    Flux<Flight> findByFromPlaceIgnoreCaseAndToPlaceIgnoreCaseAndDepartureDateAndActiveIsTrue(
            String fromPlace,
            String toPlace,
            LocalDate departureDate
    );

    @Query("{ 'flightNumber': ?0, 'departureDate': ?1, 'availableSeats': { $gte: ?2 } }")
    Mono<Flight> findAvailable(String flightNumber, LocalDate departureDate, int seats);
}