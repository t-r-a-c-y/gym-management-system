package com.gms.gym.controller;

import com.gms.gym.entity.Workout;
import com.gms.gym.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping
    public List<Workout> getWorkouts() {
        return workoutService.findAll();
    }

    @PostMapping
    public Workout addWorkout(@RequestBody Workout workout) {
        return workoutService.save(workout);
    }
}