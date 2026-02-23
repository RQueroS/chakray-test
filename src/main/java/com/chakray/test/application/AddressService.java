package com.chakray.test.application;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chakray.test.application.exceptions.NotFoundException;
import com.chakray.test.domain.Address;
import com.chakray.test.domain.Country;
import com.chakray.test.domain.User;
import com.chakray.test.domain.ports.in.DeleteAddressUseCase;
import com.chakray.test.domain.ports.in.RetrieveAddressUseCase;
import com.chakray.test.domain.ports.in.SaveAddressUseCase;
import com.chakray.test.domain.ports.out.AddressRepositoryPort;
import com.chakray.test.domain.ports.out.CountryRepositoryPort;
import com.chakray.test.domain.ports.out.UserRepositoryPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddressService implements RetrieveAddressUseCase, SaveAddressUseCase, DeleteAddressUseCase {
    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);
    private final AddressRepositoryPort addressRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final CountryRepositoryPort countryRepositoryPort;

    @Override
    public List<Address> getAllAddressesByUserId(UUID userId) {
        logger.debug("Retrieving user with ID: {} to fetch addresses", userId);
        User user = getUserById(userId);

        logger.debug("Retrieving addresses for user ID: {} from AddressRepositoryPort", userId);
        List<Address> addresses = addressRepositoryPort.findAllAddressesByUser(user);

        logger.debug("Successfully retrieved {} addresses for user ID: {}", addresses.size(), userId);
        return addresses;
    }

    @Override
    public Address saveAddress(UUID userId, Address address) {

        logger.debug("Processing address with name {} for user ID {}", address.getName(), userId);
        Country existingCountry = countryRepositoryPort.findCountryByCode(address.getCountry().getCode())
                .orElseThrow(() -> {
                    logger.warn("Country with code {} not found", address.getCountry().getCode());
                    return new NotFoundException(
                            "Country with code " + address.getCountry().getCode() + " not found");
                });
        logger.debug("Country with code {} found successfully", existingCountry.getCode());
        address.setCountry(existingCountry);

        logger.debug("Retrieving user with ID: {} to save address", userId);
        User user = getUserById(userId);

        logger.debug("Saving address with name {}", address.getName());
        Address savedAddress = addressRepositoryPort.saveAddress(user, address);

        logger.debug("Address with name {} saved successfully", savedAddress.getName());
        return savedAddress;
    }

    @Override
    public void deleteAddress(UUID userId, Long addressId) {
        logger.debug("Retrieving user with ID: {} to delete address with ID: {}", userId, addressId);

        addressRepositoryPort.findAddressByIdAndUserId(userId, addressId).orElseThrow(() -> {
            logger.warn("Address with id {} not found for user ID {}", addressId, userId);
            return new NotFoundException("Address with id " + addressId + " not found for user ID " + userId);
        });

        addressRepositoryPort.deleteAddressById(userId, addressId);
        logger.debug("Address with id {} deleted successfully for user ID {}", addressId, userId);
    }

    private User getUserById(UUID userId) {
        logger.debug("Retrieving user with id {}", userId);
        return userRepositoryPort.findUserById(userId).orElseThrow(() -> {
            logger.warn("User with id {} not found", userId);
            return new NotFoundException("User with id " + userId + " not found");
        });
    }
}
