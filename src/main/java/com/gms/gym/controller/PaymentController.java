package com.gms.gym.controller;

import com.gms.gym.dto.PaymentDTO;
import com.gms.gym.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<?> processPayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.processPayment(paymentDTO));
    }
}
