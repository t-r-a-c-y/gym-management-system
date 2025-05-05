package com.gms.gym.dto;

import lombok.Data;

@Data
public class AttendanceDTO {
    private Long id;
    private Long memberId;
    private Long scheduleId;
    private String status;
}
