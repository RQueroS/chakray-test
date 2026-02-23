package com.chakray.test.domain.ports.in;

import com.chakray.test.domain.LoginRequest;
import com.chakray.test.domain.LoginResponse;

public interface AuthUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
