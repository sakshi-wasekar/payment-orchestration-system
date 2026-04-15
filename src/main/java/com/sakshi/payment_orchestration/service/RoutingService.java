package com.sakshi.payment_orchestration.service;

import org.springframework.stereotype.Service;

import com.sakshi.payment_orchestration.model.PaymentType;

@Service
public class RoutingService {
	
	// Decide provider based on payment type
    public String route(PaymentType type) {
        return (type == PaymentType.CARD) ? "A" : "B";
    }
}
