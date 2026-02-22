package com.chakray.test.domain.ports.in;

import java.util.List;

import com.chakray.test.domain.User;

public interface RetrieveUserUseCase {
    List<User> getUsers(String sortedBy, String orderBy, String filter);
}
