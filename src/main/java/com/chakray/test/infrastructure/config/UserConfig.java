package com.chakray.test.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chakray.test.application.UserService;
import com.chakray.test.domain.ports.in.RetrieveUserUseCase;
import com.chakray.test.domain.ports.in.SaveUserUseCase;
import com.chakray.test.domain.ports.out.CountryRepositoryPort;
import com.chakray.test.domain.ports.out.UserRepositoryPort;

@Configuration
public class UserConfig {
    @Bean
    public RetrieveUserUseCase retrieveUserUseCase(UserRepositoryPort userRepositoryPort,
            CountryRepositoryPort countryRepositoryPort) {
        return new UserService(userRepositoryPort, countryRepositoryPort);
    }

    @Bean
    public SaveUserUseCase saveUserUseCase(UserRepositoryPort userRepositoryPort,
            CountryRepositoryPort countryRepositoryPort) {
        return new UserService(userRepositoryPort, countryRepositoryPort);
    }
}
