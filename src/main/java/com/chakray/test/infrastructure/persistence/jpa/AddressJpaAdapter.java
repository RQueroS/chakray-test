package com.chakray.test.infrastructure.persistence.jpa;

import java.util.List;

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

    @Override
    public List<Address> findAllAddressesByUser(User user) {
        logger.debug("Fetching all addresses from the database");
        List<AddressEntity> addressEntities = addressJpaRepository.findAllByUserId(user.getId());

        logger.debug("Mapping AddressEntity to Address domain objects");
        List<Address> addresses = addressEntities.stream().map(addressJpaMapper::toDomain).toList();

        logger.debug("Successfully mapped {} AddressEntity to Address", addresses.size());
        return addresses;
    }
}
