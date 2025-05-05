package com.gms.gym.controller;

import com.gms.gym.dto.ScheduleDTO;
import com.gms.gym.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return ResponseEntity.ok(scheduleService.createSchedule(scheduleDTO));
    }
}
