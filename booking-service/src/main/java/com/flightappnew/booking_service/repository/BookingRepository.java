package com.flightappnew.booking_service.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.flightappnew.booking_service.entity.Booking;

public interface BookingRepository extends ReactiveMongoRepository<Booking, String> {
}
