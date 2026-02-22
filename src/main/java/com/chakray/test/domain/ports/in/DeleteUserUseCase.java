package com.chakray.test.domain.ports.in;

import java.util.UUID;

public interface DeleteUserUseCase {
    void deleteUser(UUID id);
}
