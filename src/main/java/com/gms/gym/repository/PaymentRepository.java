package com.gms.gym.repository;

import com.gms.gym.entity.MembershipPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<MembershipPayment, Long> {
}
