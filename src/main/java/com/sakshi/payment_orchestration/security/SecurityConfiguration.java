package com.sakshi.payment_orchestration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http 
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
            
            // Configure endpoint authorization
            .authorizeHttpRequests(auth -> auth
            		
                // Public endpoints
                .requestMatchers("/payments/**", "/h2-console/**").permitAll()

               // All other endpoints require authentication
                .anyRequest().authenticated()
            );

        return http.build();
    }

}
