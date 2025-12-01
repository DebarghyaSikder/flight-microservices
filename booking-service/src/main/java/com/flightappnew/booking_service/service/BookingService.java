package com.flightappnew.booking_service.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.flightappnew.booking_service.client.FlightClient;
import com.flightappnew.booking_service.dto.BookingRequest;
import com.flightappnew.booking_service.entity.Booking;
import com.flightappnew.booking_service.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightClient flightClient;

    public BookingService(BookingRepository bookingRepository, FlightClient flightClient) {
        this.bookingRepository = bookingRepository;
        this.flightClient = flightClient;
    }

    public Booking createBooking(BookingRequest request) {

        boolean available = flightClient.checkAvailability(
                request.getFlightNumber(),
                request.getJourneyDate(),
                request.getSeats()
        );

        if (!available) {
            throw new IllegalStateException("Seats not available for this flight");
        }

        BigDecimal totalPrice =
                request.getPricePerSeat().multiply(BigDecimal.valueOf(request.getSeats()));

        Booking booking = Booking.builder()
                .bookingReference(UUID.randomUUID().toString())
                .passengerName(request.getPassengerName())
                .passengerEmail(request.getPassengerEmail())
                .flightNumber(request.getFlightNumber())
                .fromPlace(request.getFromPlace())
                .toPlace(request.getToPlace())
                .journeyDate(request.getJourneyDate())
                .seatsBooked(request.getSeats())
                .totalPrice(totalPrice)
                .status("CREATED")
                .createdAt(LocalDateTime.now())
                .build();

        return bookingRepository.save(booking);
    }

    public Booking getBooking(String id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found: " + id));
    }
}