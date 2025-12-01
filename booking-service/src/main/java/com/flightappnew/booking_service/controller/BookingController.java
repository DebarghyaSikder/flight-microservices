package com.flightappnew.booking_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flightappnew.booking_service.dto.BookingRequest;
import com.flightappnew.booking_service.entity.Booking;
import com.flightappnew.booking_service.service.BookingService;


@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest req) {
        Booking booking = bookingService.createBooking(req);
        return ResponseEntity.ok(booking);
    }

}
