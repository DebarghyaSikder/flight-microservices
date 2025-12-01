package com.flightappnew.flight_service.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.flightappnew.flight_service.model.Flight;
import com.flightappnew.flight_service.repository.FlightRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Mono<Flight> addFlight(Flight flight) {
        // when we add new flight, availableSeats = totalSeats if not set
        if (flight.getAvailableSeats() == null) {
            flight.setAvailableSeats(flight.getTotalSeats());
        }
        return flightRepository.save(flight);
    }

    public Flux<Flight> searchFlights(String from, String to, LocalDate date) {
        return flightRepository.findByFromPlaceAndToPlaceAndDepartureDate(from, to, date);
    }

    public Flux<Flight> findAll() {
        return flightRepository.findAll();
    }
}