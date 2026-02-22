package com.chakray.test.domain.ports.in;

import java.util.UUID;

import com.chakray.test.domain.Address;

public interface SaveAddressUseCase {
    Address saveAddress(UUID userId, Address address);
}
