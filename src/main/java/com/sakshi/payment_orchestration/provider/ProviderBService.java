package com.sakshi.payment_orchestration.provider;

import org.springframework.stereotype.Service;

@Service
public class ProviderBService {
	
	// Simulates external API call
	public boolean process() {
        return Math.random() > 0.2;
    }
}
