package com.sakshi.payment_orchestration.exception;

public class PaymentNotFoundException extends RuntimeException{

	public PaymentNotFoundException(String message) {
        super(message);
    }
}
