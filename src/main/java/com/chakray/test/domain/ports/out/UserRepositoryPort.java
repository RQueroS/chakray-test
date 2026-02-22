package com.chakray.test.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.chakray.test.domain.User;

public interface UserRepositoryPort {
    List<User> findAllUsers(String sortedBy, String orderBy, String filter);

    User saveUser(User user);

    Optional<User> findUserByTaxId(String taxId);

    Optional<User> findUserById(String id);

    void deleteUserById(String id);
}
