package com.gms.gym.dto;

import lombok.Data;

@Data
public class ScheduleDTO {
    private Long id;
    private Long trainerId;
    private String className;
    private String startTime;
    private String endTime;
    private Integer capacity;
    private Long branchId;
}