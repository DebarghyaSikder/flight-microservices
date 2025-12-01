package com.flightappnew.flight_service.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.flightappnew.flight_service.model.Flight;

import reactor.core.publisher.Flux;

@Repository
public interface FlightRepository extends ReactiveMongoRepository<Flight, String> {

    Flux<Flight> findByFromPlaceAndToPlaceAndDepartureDate(
            String fromPlace,
            String toPlace,
            LocalDate departureDate
    );
}