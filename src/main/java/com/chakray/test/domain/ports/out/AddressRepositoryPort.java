package com.chakray.test.domain.ports.out;

import java.util.List;

import com.chakray.test.domain.Address;
import com.chakray.test.domain.User;

public interface AddressRepositoryPort {
    List<Address> findAllAddressesByUser(User user);

    Address saveAddress(User user, Address address);
}
