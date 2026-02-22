package com.chakray.test.domain.ports.in;

import java.util.List;
import java.util.UUID;

import com.chakray.test.domain.Address;

public interface RetrieveAddressUseCase {
    List<Address> getAllAddressesByUserId(UUID userId);
}
