package com.chakray.test.domain.ports.in;

import com.chakray.test.domain.User;

public interface SaveUserUseCase {
    User saveUser(User user);

    User updateUser(String userId, User user);
}
