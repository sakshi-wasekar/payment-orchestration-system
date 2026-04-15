package com.sakshi.payment_orchestration.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sakshi.payment_orchestration.dto.PaymentRequestDTO;
import com.sakshi.payment_orchestration.dto.PaymentResponseDTO;
import com.sakshi.payment_orchestration.entity.Payment;
import com.sakshi.payment_orchestration.exception.PaymentNotFoundException;
import com.sakshi.payment_orchestration.model.PaymentStatus;
import com.sakshi.payment_orchestration.model.PaymentType;
import com.sakshi.payment_orchestration.provider.ProviderAService;
import com.sakshi.payment_orchestration.provider.ProviderBService;
import com.sakshi.payment_orchestration.repository.PaymentRepository;

@Service
public class PaymentService {
	private final PaymentRepository repository;
    private final RoutingService routingService;
    private final ProviderAService providerA;
    private final ProviderBService providerB;
    private final IdempotencyService idempotencyService;

    public PaymentService(PaymentRepository repository,
                          RoutingService routingService,
                          ProviderAService providerA,
                          ProviderBService providerB,
                          IdempotencyService idempotencyService) {

        this.repository = repository;
        this.routingService = routingService;
        this.providerA = providerA;
        this.providerB = providerB;
        this.idempotencyService = idempotencyService;
    }

    public PaymentResponseDTO createPayment(PaymentRequestDTO request) {

        // Step 1: Check idempotency
        Optional<Payment> existing =
                idempotencyService.checkExisting(request.getIdempotencyKey());

        if (existing.isPresent()) {
            return mapToResponse(existing.get());
        }

        // Step 2: Create payment
        
        PaymentType type;

        try {
            type = PaymentType.valueOf(request.getPaymentType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid payment type");
        }
        
        String provider = routingService.route(type);

        Payment payment = new Payment(
            request.getAmount(),
            request.getCurrency(),
            type,
            PaymentStatus.PENDING,
            provider,   // ✔ set here directly
            request.getIdempotencyKey()
        );

        // Step 4: Retry logic
        boolean success = false;
        int attempt = 0;

        while (attempt < 3 && !success) {
            attempt++;

            if ("A".equals(provider)) {
                success = providerA.process();
            } else {
                success = providerB.process();
            }
        }

        // Step 5: Update status
        payment.setStatus(success ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);

        // Step 6: Save
        repository.save(payment);

        return mapToResponse(payment);
    }
    
    public PaymentResponseDTO getPayment(Long id) {

        Payment payment = repository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));

        return mapToResponse(payment);
    }

    private PaymentResponseDTO mapToResponse(Payment payment) {
        return new PaymentResponseDTO(
                payment.getId(),
                payment.getStatus().name(),
                payment.getProvider()
        );
    }
    
}