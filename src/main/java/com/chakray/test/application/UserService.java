package com.chakray.test.application;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chakray.test.domain.User;
import com.chakray.test.domain.ports.in.RetrieveUserUseCase;
import com.chakray.test.domain.ports.out.UserRepositoryPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService implements RetrieveUserUseCase {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public List<User> getUsers(String sortedBy, String orderBy) {
        logger.debug("Retrieving users from UserRepositoryPort");
        List<User> users = userRepositoryPort.findAllUsers(sortedBy, orderBy);

        logger.debug("Successfully retrieved {} users", users.size());
        return users;
    }
}
