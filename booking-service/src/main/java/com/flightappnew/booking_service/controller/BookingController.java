package com.flightappnew.booking_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.flightappnew.booking_service.dto.BookingRequest;
import com.flightappnew.booking_service.entity.Booking;
import com.flightappnew.booking_service.service.BookingService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Booking> createBooking(@Valid @RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }
}
