package com.flightappnew.booking_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flightappnew.booking_service.entity.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {
}