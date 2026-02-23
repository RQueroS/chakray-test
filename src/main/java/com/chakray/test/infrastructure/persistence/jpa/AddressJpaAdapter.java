package com.chakray.test.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.chakray.test.domain.Address;
import com.chakray.test.domain.User;
import com.chakray.test.domain.ports.out.AddressRepositoryPort;
import com.chakray.test.infrastructure.persistence.jpa.entity.AddressEntity;
import com.chakray.test.infrastructure.persistence.jpa.repository.AddressJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddressJpaAdapter implements AddressRepositoryPort {
    private static final Logger logger = LoggerFactory.getLogger(AddressJpaAdapter.class);
    private final AddressJpaRepository addressJpaRepository;
    private final AddressJpaMapper addressJpaMapper;
    private final UserJpaMapper userJpaMapper;

    @Override
    public List<Address> findAllAddressesByUser(User user) {
        logger.debug("Fetching all addresses from the database");
        List<AddressEntity> addressEntities = addressJpaRepository.findAllByUserId(user.getId());

        logger.debug("Mapping AddressEntity to Address domain objects");
        List<Address> addresses = addressEntities.stream().map(addressJpaMapper::toDomain).toList();

        logger.debug("Successfully mapped {} AddressEntity to Address", addresses.size());
        return addresses;
    }

    @Override
    public Address saveAddress(User user, Address address) {
        logger.debug("Saving address with name {}", address.getName());
        AddressEntity addressEntity = addressJpaMapper.toEntity(address);

        logger.debug("Saving AddressEntity to the database");
        addressEntity.setUser(userJpaMapper.toEntity(user));
        AddressEntity savedEntity = addressJpaRepository.save(addressEntity);

        logger.debug("Mapping saved AddressEntity back to Address domain object");
        Address savedAddress = addressJpaMapper.toDomain(savedEntity);

        logger.debug("Successfully saved address with name {}", savedAddress.getName());
        return savedAddress;
    }

    @Override
    public void deleteAddressById(UUID userId, Long addressId) {
        logger.debug("Deleting address with ID {} for user ID {}", addressId, userId);
        addressJpaRepository.deleteByIdAndUserId(addressId, userId);

        logger.debug("Successfully deleted address with ID {} for user ID {}", addressId, userId);
    }

    @Override
    public Optional<Address> findAddressByIdAndUserId(UUID userId, Long addressId) {
        logger.debug("Fetching address with ID {} for user ID {} from the database", addressId, userId);
        Optional<AddressEntity> addressEntity = addressJpaRepository.findByIdAndUserId(addressId, userId);

        logger.debug("Mapping AddressEntity to Address domain object if present");
        return addressEntity.map(addressJpaMapper::toDomain);
    }
}
