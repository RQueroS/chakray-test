package com.chakray.test.application;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chakray.test.application.exceptions.NotFoundException;
import com.chakray.test.domain.Address;
import com.chakray.test.domain.User;
import com.chakray.test.domain.ports.in.RetrieveAddressUseCase;
import com.chakray.test.domain.ports.out.AddressRepositoryPort;
import com.chakray.test.domain.ports.out.UserRepositoryPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddressService implements RetrieveAddressUseCase {
    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);
    private final AddressRepositoryPort addressRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public List<Address> getAllAddressesByUserId(UUID userId) {
        logger.debug("Retrieving user with ID: {} to fetch addresses", userId);
        User user = getUserById(userId);

        logger.debug("Retrieving addresses for user ID: {} from AddressRepositoryPort", userId);
        List<Address> addresses = addressRepositoryPort.findAllAddressesByUser(user);

        logger.debug("Successfully retrieved {} addresses for user ID: {}", addresses.size(), userId);
        return addresses;
    }

    private User getUserById(UUID id) {
        logger.debug("Retrieving user with id {}", id);
        return userRepositoryPort.findUserById(id).orElseThrow(() -> {
            logger.warn("User with id {} not found", id);
            return new NotFoundException("User with id " + id + " not found");
        });
    }
}
