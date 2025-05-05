package com.gms.gym.service;

import com.gms.gym.dto.ScheduleDTO;
import com.gms.gym.entity.Branch;
import com.gms.gym.entity.Schedule;
import com.gms.gym.entity.User;
import com.gms.gym.repository.BranchRepository;
import com.gms.gym.repository.ScheduleRepository;
import com.gms.gym.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository,
                           BranchRepository branchRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
    }

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        User trainer = userRepository.findById(scheduleDTO.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
        Branch branch = branchRepository.findById(scheduleDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        schedule.setTrainer(trainer);
        schedule.setBranch(branch);
        schedule.setClassName(scheduleDTO.getClassName());
        schedule.setStartTime(LocalDateTime.parse(scheduleDTO.getStartTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        schedule.setEndTime(LocalDateTime.parse(scheduleDTO.getEndTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        schedule.setCapacity(scheduleDTO.getCapacity());
        schedule = scheduleRepository.save(schedule);
        scheduleDTO.setId(schedule.getId());
        return scheduleDTO;
    }
}
