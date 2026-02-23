package com.chakray.test.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chakray.test.application.exceptions.BadRequestException;
import com.chakray.test.domain.LoginRequest;
import com.chakray.test.domain.User;
import com.chakray.test.domain.ports.in.AuthUseCase;
import com.chakray.test.domain.ports.out.PasswordEncoderPort;
import com.chakray.test.domain.ports.out.UserRepositoryPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthService implements AuthUseCase {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public String login(LoginRequest loginRequest) {
        User user = userRepositoryPort.findUserByTaxId(loginRequest.getUsername()).orElseThrow(() -> {
            logger.warn("User with taxId {} not found", loginRequest.getUsername());
            return new BadRequestException("Username or password is incorrect");
        });

        if (!passwordEncoderPort.matches(loginRequest.getPassword(), user.getPassword())) {
            logger.warn("Invalid password for user with taxId {}", loginRequest.getUsername());
            throw new BadRequestException("Username or password is incorrect");
        }

        return "token-dummy";
    }

}
