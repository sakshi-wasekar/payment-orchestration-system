package com.sakshi.payment_orchestration.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sakshi.payment_orchestration.entity.Payment;
import com.sakshi.payment_orchestration.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdempotencyService {
	
	private final PaymentRepository payRepository;

	public IdempotencyService(PaymentRepository payRepository) {
        this.payRepository = payRepository;
    }

	
    // Check if request already processed
    public Optional<Payment> checkExisting(String key) {
        return payRepository.findByIdempotencyKey(key);
    }
}
