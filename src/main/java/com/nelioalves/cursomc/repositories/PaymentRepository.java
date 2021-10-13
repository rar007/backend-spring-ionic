package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
