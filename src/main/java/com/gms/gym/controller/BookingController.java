package com.gms.gym.controller;

import com.gms.gym.dto.BookingDTO;
import com.gms.gym.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> bookClass(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.bookClass(bookingDTO));
    }
}
