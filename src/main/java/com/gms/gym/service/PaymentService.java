package com.gms.gym.service;

import com.gms.gym.dto.PaymentDTO;
import com.gms.gym.entity.MembershipPayment;
import com.gms.gym.entity.User;
import com.gms.gym.repository.PaymentRepository;
import com.gms.gym.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    public PaymentDTO processPayment(PaymentDTO paymentDTO) {
        MembershipPayment payment = new MembershipPayment();
        User member = userRepository.findById(paymentDTO.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        payment.setMember(member);
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setStatus(MembershipPayment.Status.valueOf(paymentDTO.getStatus()));
        payment = paymentRepository.save(payment);
        paymentDTO.setId(payment.getId());
        return paymentDTO;
    }
}
