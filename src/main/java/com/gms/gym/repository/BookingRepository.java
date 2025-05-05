package com.gms.gym.repository;

import com.gms.gym.entity.ClassBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<ClassBooking, Long> {
}
