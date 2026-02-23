package com.chakray.test.domain.ports.in;

import com.chakray.test.domain.LoginRequest;

public interface AuthUseCase {
    String login(LoginRequest loginRequest);
}
