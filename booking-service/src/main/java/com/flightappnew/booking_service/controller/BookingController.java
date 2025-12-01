package com.flightappnew.booking_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flightappnew.booking_service.dto.BookingRequest;
import com.flightappnew.booking_service.entity.Booking;
import com.flightappnew.booking_service.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingRequest request) {
        Booking booking = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable String id) {
        return ResponseEntity.ok(bookingService.getBooking(id));
    }
}