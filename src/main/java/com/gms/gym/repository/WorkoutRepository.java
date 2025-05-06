package com.gms.gym.repository;


import com.gms.gym.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
}
