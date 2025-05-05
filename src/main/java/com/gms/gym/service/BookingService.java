package com.gms.gym.service;

import com.gms.gym.dto.BookingDTO;
import com.gms.gym.entity.ClassBooking;
import com.gms.gym.entity.Schedule;
import com.gms.gym.entity.User;
import com.gms.gym.repository.BookingRepository;
import com.gms.gym.repository.ScheduleRepository;
import com.gms.gym.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository,
                          ScheduleRepository scheduleRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public BookingDTO bookClass(BookingDTO bookingDTO) {
        ClassBooking booking = new ClassBooking();
        User member = userRepository.findById(bookingDTO.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Schedule schedule = scheduleRepository.findById(bookingDTO.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        booking.setMember(member);
        booking.setSchedule(schedule);
        booking.setStatus(ClassBooking.Status.valueOf(bookingDTO.getStatus()));
        booking = bookingRepository.save(booking);
        bookingDTO.setId(booking.getId());
        return bookingDTO;
    }
}
