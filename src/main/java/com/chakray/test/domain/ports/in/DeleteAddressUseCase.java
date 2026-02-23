package com.chakray.test.domain.ports.in;

import java.util.UUID;

public interface DeleteAddressUseCase {
    void deleteAddress(UUID userId, Long addressId);
}
