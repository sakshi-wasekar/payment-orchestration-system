package com.sakshi.payment_orchestration.dto;

public class PaymentResponseDTO {

    private Long paymentId;
    private String status;		// Final payment status (SUCCESS / FAILED)
    private String provider;    // Selected provider (A / B)
    
	public PaymentResponseDTO(Long paymentId, String status, String provider) {
		
		this.paymentId = paymentId;
		this.status = status;
		this.provider = provider;
	}
	
	public Long getPaymentId() {
		return paymentId;
	}
	public String getStatus() {
		return status;
	}
	public String getProvider() {
		return provider;
	}

    
}