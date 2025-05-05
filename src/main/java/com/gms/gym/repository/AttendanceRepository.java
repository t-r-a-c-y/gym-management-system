package com.gms.gym.repository;

import com.gms.gym.entity.ClassAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<ClassAttendance, Long> {
}
