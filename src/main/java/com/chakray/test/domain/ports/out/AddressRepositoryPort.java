package com.chakray.test.domain.ports.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.chakray.test.domain.Address;
import com.chakray.test.domain.User;

public interface AddressRepositoryPort {
    List<Address> findAllAddressesByUser(User user);

    Address saveAddress(User user, Address address);

    void deleteAddressById(UUID userId, Long addressId);

    Optional<Address> findAddressByIdAndUserId(UUID userId, Long addressId);
}
