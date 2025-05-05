package com.gms.gym.controller;

import com.gms.gym.dto.AttendanceDTO;
import com.gms.gym.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<?> markAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        return ResponseEntity.ok(attendanceService.markAttendance(attendanceDTO));
    }
}
