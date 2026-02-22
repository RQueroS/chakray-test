package com.chakray.test.domain.ports.in;

import java.util.UUID;

import com.chakray.test.domain.User;

public interface SaveUserUseCase {
    User saveUser(User user);

    User updateUser(UUID userId, User user);
}
