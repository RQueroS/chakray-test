package com.chakray.test.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chakray.test.application.AddressService;
import com.chakray.test.domain.ports.in.RetrieveAddressUseCase;
import com.chakray.test.domain.ports.in.SaveAddressUseCase;
import com.chakray.test.domain.ports.out.AddressRepositoryPort;
import com.chakray.test.domain.ports.out.CountryRepositoryPort;
import com.chakray.test.domain.ports.out.UserRepositoryPort;

@Configuration
public class AddressConfig {
    @Bean
    public RetrieveAddressUseCase retrieveAddressUseCase(AddressRepositoryPort addressRepositoryPort,
            UserRepositoryPort userRepositoryPort, CountryRepositoryPort countryRepositoryPort) {
        return new AddressService(addressRepositoryPort, userRepositoryPort, countryRepositoryPort);
    }

    @Bean
    public SaveAddressUseCase saveAddressUseCase(AddressRepositoryPort addressRepositoryPort,
            UserRepositoryPort userRepositoryPort, CountryRepositoryPort countryRepositoryPort) {
        return new AddressService(addressRepositoryPort, userRepositoryPort, countryRepositoryPort);
    }
}
