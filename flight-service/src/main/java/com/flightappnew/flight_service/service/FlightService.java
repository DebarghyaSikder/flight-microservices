package com.flightappnew.flight_service.service;



import com.flightappnew.flight_service.dto.FlightRequest;
import com.flightappnew.flight_service.dto.FlightResponse;
import com.flightappnew.flight_service.dto.FlightSearchRequest;
import com.flightappnew.flight_service.entity.Flight;
import com.flightappnew.flight_service.repository.FlightRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public Mono<FlightResponse> createFlight(FlightRequest request) {
        Flight flight = Flight.builder()
                .airlineName(request.getAirlineName())
                .flightNumber(request.getFlightNumber())
                .fromPlace(request.getFromPlace())
                .toPlace(request.getToPlace())
                .departureDate(request.getDepartureDate())
                .departureTime(request.getDepartureTime())
                .arrivalDate(request.getArrivalDate())
                .arrivalTime(request.getArrivalTime())
                .price(request.getPrice())
                .totalSeats(request.getTotalSeats())
                .availableSeats(request.getAvailableSeats())
                .active(request.isActive())
                .build();

        return flightRepository.save(flight)
                .map(this::toResponse);
    }

    public Flux<FlightResponse> searchFlights(FlightSearchRequest request) {
        return flightRepository
                .findByFromPlaceIgnoreCaseAndToPlaceIgnoreCaseAndDepartureDateAndActiveIsTrue(
                        request.getFromPlace(),
                        request.getToPlace(),
                        request.getDepartureDate()
                )
                .map(this::toResponse);
    }

    public Mono<FlightResponse> getById(String id) {
        return flightRepository.findById(id)
                .map(this::toResponse);
    }

    private FlightResponse toResponse(Flight flight) {
        return FlightResponse.builder()
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
                .totalSeats(flight.getTotalSeats())
                .availableSeats(flight.getAvailableSeats())
                .active(flight.isActive())
                .build();
    }
}