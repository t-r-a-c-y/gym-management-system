package com.gms.gym.controller;

import com.gms.gym.entity.Workout;
import com.gms.gym.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepository;

    // Create a new workout
    @PostMapping
    public Workout addWorkout(@RequestBody Workout workout) {
        return workoutRepository.save(workout);
    }

    // Read all workouts
    @GetMapping
    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }

    // Read a single workout by ID
    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long id) {
        Optional<Workout> workout = workoutRepository.findById(id);
        return workout.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing workout
    @PutMapping("/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @RequestBody Workout updatedWorkout) {
        Optional<Workout> existingWorkout = workoutRepository.findById(id);
        if (existingWorkout.isPresent()) {
            Workout workout = existingWorkout.get();
            workout.setType(updatedWorkout.getType());
            workout.setDuration(updatedWorkout.getDuration());
            return ResponseEntity.ok(workoutRepository.save(workout));
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a workout
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        if (workoutRepository.existsById(id)) {
            workoutRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}