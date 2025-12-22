package com.flightappnew.flight_service.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.flightappnew.flight_service.dto.FlightRequest;
import com.flightappnew.flight_service.dto.FlightResponse;
import com.flightappnew.flight_service.dto.FlightSearchRequest;
import com.flightappnew.flight_service.entity.Flight;
import com.flightappnew.flight_service.repository.FlightRepository;
import com.flightappnew.flight_service.service.FlightService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    // ---------- Create Flight ----------
    @Override
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
                .availableSeats(request.getTotalSeats())
                .active(true)
                .build();

        return flightRepository.save(flight)
                .map(this::mapToResponse);
    }

    @Override
    public Flux<FlightResponse> searchFlights(FlightSearchRequest request) {

        LocalDate date = request.getDepartureDate();

        return flightRepository
            .findByFromPlaceIgnoreCaseAndToPlaceIgnoreCaseAndDepartureDateBetweenAndActiveTrue(
                request.getFromPlace(),
                request.getToPlace(),
                date,
                date.plusDays(1)   
            )
            .map(this::mapToResponse);
    }



    // ---------- Get by ID ----------
    @Override
    public Mono<FlightResponse> getById(String id) {
        return flightRepository.findById(id)
                .map(this::mapToResponse);
    }

    // ---------- Availability ----------
    @Override
    public Mono<Boolean> checkAvailability(String flightNumber, LocalDate departureDate, int seats) {
        return flightRepository
                .findByFlightNumberAndDepartureDateAndAvailableSeatsGreaterThanEqual(
                        flightNumber,
                        departureDate,
                        seats
                )
                .hasElement();
    }

    // ---------- Mapper ----------
    private FlightResponse mapToResponse(Flight flight) {
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
                .availableSeats(flight.getAvailableSeats())
                .build();
    }
}
