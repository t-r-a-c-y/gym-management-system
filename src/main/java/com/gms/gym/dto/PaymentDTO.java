package com.gms.gym.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private Long id;
    private Long memberId;
    private Double amount;
    private String paymentDate;
    private String status;
}
