package com.gms.gym.dto;

import lombok.Data;

@Data
public class BookingDTO {
    private Long id;
    private Long memberId;
    private Long scheduleId;
    private String status;
}