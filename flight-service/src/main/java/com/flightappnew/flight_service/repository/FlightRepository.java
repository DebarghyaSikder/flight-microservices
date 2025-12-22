package com.flightappnew.flight_service.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.flightappnew.flight_service.entity.Flight;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FlightRepository extends ReactiveMongoRepository<Flight, String> {

    // For availability check
    Mono<Flight> findByFlightNumberAndDepartureDateAndAvailableSeatsGreaterThanEqual(
            String flightNumber,
            LocalDate departureDate,
            int availableSeats
    );

    // For Rabbit listener seat update
    Mono<Flight> findByFlightNumberAndDepartureDate(
            String flightNumber,
            LocalDate departureDate
    );

    // For search API
    Flux<Flight> findByFromPlaceIgnoreCaseAndToPlaceIgnoreCaseAndDepartureDateBetweenAndActiveTrue(
    	    String fromPlace,
    	    String toPlace,
    	    LocalDate start,
    	    LocalDate end
    	);



}
