package com.flightappnew.flight_service.service.impl;

import org.springframework.stereotype.Service;

import com.flightappnew.flight_service.dto.FlightSearchRequest;
import com.flightappnew.flight_service.dto.FlightSearchResponse;
import com.flightappnew.flight_service.model.Flight;
import com.flightappnew.flight_service.repository.FlightRepository;
import com.flightappnew.flight_service.service.FlightService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public Mono<Flight> addFlight(Flight flight) {
        if (flight.getActive() == null) {
            flight.setActive(true);
        }
        if (flight.getAvailableSeats() == null && flight.getTotalSeats() != null) {
            flight.setAvailableSeats(flight.getTotalSeats());
        }
        return flightRepository.save(flight);
    }

    @Override
    public Flux<FlightSearchResponse> searchFlights(FlightSearchRequest request) {
        return flightRepository
                .findByFromPlaceAndToPlaceAndDepartureDateAndActiveTrue(
                        request.getFromPlace(),
                        request.getToPlace(),
                        request.getDepartureDate()
                )
                .map(flight -> FlightSearchResponse.builder()
                        .id(flight.getId())
                        .airlineName(flight.getAirlineName())
                        .flightNumber(flight.getFlightNumber())
                        .fromPlace(flight.getFromPlace())
                        .toPlace(flight.getToPlace())
                        .departureDate(flight.getDepartureDate())
                        .departureTime(flight.getDepartureTime())
                        .arrivalDate(flight.getArrivalDate())
                        .arrivalTime(flight.getArrivalTime())
                        .price(flight.getPrice())
                        .availableSeats(flight.getAvailableSeats())
                        .build()
                );
    }
}