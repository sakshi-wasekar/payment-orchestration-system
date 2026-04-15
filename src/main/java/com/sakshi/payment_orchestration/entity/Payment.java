package com.sakshi.payment_orchestration.entity;

import com.sakshi.payment_orchestration.model.PaymentStatus;
import com.sakshi.payment_orchestration.model.PaymentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Transaction amount
    private Double amount;

    // Currency type
    private String currency;

    // Payment method (CARD / UPI)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    // Current status of payment
    @Enumerated(EnumType.STRING)
    private PaymentStatus  status;

    // Selected provider (A / B)
    private String provider;

    // Prevent duplicate transactions
    @Column(unique = true, nullable = false)
    private String idempotencyKey;

    public Payment() {}
    
	public Payment(Double amount, String currency, PaymentType paymentType, PaymentStatus status, String provider,
			String idempotencyKey) {

		this.amount = amount;
		this.currency = currency;
		this.paymentType = paymentType;
		this.status = status;
		this.provider = provider;
		this.idempotencyKey = idempotencyKey;
	}

	
	
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public String getProvider() {
		return provider;
	}

	public String getIdempotencyKey() {
		return idempotencyKey;
	}
}
