package com.sakshi.payment_orchestration.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakshi.payment_orchestration.dto.PaymentRequestDTO;
import com.sakshi.payment_orchestration.dto.PaymentResponseDTO;
import com.sakshi.payment_orchestration.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }
    
    // create a new payment
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(
            @Valid @RequestBody PaymentRequestDTO request) {

        return ResponseEntity.ok(service.createPayment(request));
    }

    // fetch payment details by ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPayment(@PathVariable Long id) {

        return ResponseEntity.ok(service.getPayment(id));
    }
}
