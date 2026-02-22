package com.chakray.test.application;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chakray.test.application.exceptions.ConflictException;
import com.chakray.test.domain.User;
import com.chakray.test.domain.ports.in.RetrieveUserUseCase;
import com.chakray.test.domain.ports.in.SaveUserUseCase;
import com.chakray.test.domain.ports.out.UserRepositoryPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService implements RetrieveUserUseCase, SaveUserUseCase {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public List<User> getUsers(String sortedBy, String orderBy, String filter) {
        logger.debug("Retrieving users from UserRepositoryPort");
        List<User> users = userRepositoryPort.findAllUsers(sortedBy, orderBy, filter);

        logger.debug("Successfully retrieved {} users", users.size());
        return users;
    }

    @Override
    public User saveUser(User user) {
        logger.debug("Saving user with taxId {}", user.getTaxId());
        userRepositoryPort.findUserByTaxId(user.getTaxId()).ifPresent(existingUser -> {
            logger.warn("User with taxId {} already exists", user.getTaxId());
            throw new ConflictException("User with taxId " + user.getTaxId() + " already exists");
        });

        logger.debug("No existing user with taxId {}, proceeding to save",
                user.getTaxId());
        User savedUser = userRepositoryPort.saveUser(user);

        logger.debug("Successfully saved user with id {}", savedUser.getId());
        return savedUser;
    }
}
