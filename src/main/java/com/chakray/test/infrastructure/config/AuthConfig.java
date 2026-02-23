package com.chakray.test.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chakray.test.application.AuthService;
import com.chakray.test.domain.ports.in.AuthUseCase;
import com.chakray.test.domain.ports.out.PasswordEncoderPort;
import com.chakray.test.domain.ports.out.UserRepositoryPort;

@Configuration
public class AuthConfig {
    @Bean
    public AuthUseCase authUseCase(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
        return new AuthService(userRepositoryPort, passwordEncoderPort);
    }
}
