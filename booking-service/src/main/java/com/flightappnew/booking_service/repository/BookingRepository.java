package com.flightappnew.booking_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightappnew.booking_service.entity.Booking;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    // You can add custom query methods later if needed
}