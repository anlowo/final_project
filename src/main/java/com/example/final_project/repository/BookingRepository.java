package com.example.final_project.repository;

import com.example.final_project.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCheckInDate(Date checkInDate);
}
