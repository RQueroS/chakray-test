package com.chakray.test.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chakray.test.application.AddressService;
import com.chakray.test.domain.ports.in.RetrieveAddressUseCase;
import com.chakray.test.domain.ports.out.AddressRepositoryPort;
import com.chakray.test.domain.ports.out.UserRepositoryPort;

@Configuration
public class AddressConfig {
    @Bean
    public RetrieveAddressUseCase retrieveAddressUseCase(AddressRepositoryPort addressRepositoryPort,
            UserRepositoryPort userRepositoryPort) {
        return new AddressService(addressRepositoryPort, userRepositoryPort);
    }
}
