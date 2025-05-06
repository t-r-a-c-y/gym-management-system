package com.gms.gym.service;

import com.gms.gym.entity.Workout;
import com.gms.gym.repository.WorkoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkoutService {
    private WorkoutRepository workoutRepository;

    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }
}
