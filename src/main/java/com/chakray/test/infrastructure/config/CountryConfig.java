package com.chakray.test.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chakray.test.application.CountryService;
import com.chakray.test.domain.ports.in.RetrieveCountryUseCase;
import com.chakray.test.domain.ports.out.CountryRepositoryPort;

@Configuration
public class CountryConfig {
    @Bean
    public RetrieveCountryUseCase retrieveCountryUseCase(CountryRepositoryPort countryRepositoryPort) {
        return new CountryService(countryRepositoryPort);
    }
}
