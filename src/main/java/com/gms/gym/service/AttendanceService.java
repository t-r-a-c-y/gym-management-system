package com.gms.gym.service;

import com.gms.gym.dto.AttendanceDTO;
import com.gms.gym.entity.ClassAttendance;
import com.gms.gym.entity.Schedule;
import com.gms.gym.entity.User;
import com.gms.gym.repository.AttendanceRepository;
import com.gms.gym.repository.ScheduleRepository;
import com.gms.gym.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, UserRepository userRepository,
                             ScheduleRepository scheduleRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public AttendanceDTO markAttendance(AttendanceDTO attendanceDTO) {
        ClassAttendance attendance = new ClassAttendance();
        User member = userRepository.findById(attendanceDTO.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Schedule schedule = scheduleRepository.findById(attendanceDTO.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        attendance.setMember(member);
        attendance.setSchedule(schedule);
        attendance.setStatus(ClassAttendance.Status.valueOf(attendanceDTO.getStatus()));
        attendance = attendanceRepository.save(attendance);
        attendanceDTO.setId(attendance.getId());
        return attendanceDTO;
    }
}
