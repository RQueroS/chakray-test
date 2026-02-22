package com.chakray.test.domain.ports.out;

import java.util.List;

import com.chakray.test.domain.User;

public interface UserRepositoryPort {
    List<User> findAllUsers();
}
