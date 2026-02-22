package com.chakray.test.infrastructure.persistence.jpa;

import org.mapstruct.Mapper;

import com.chakray.test.domain.Address;
import com.chakray.test.infrastructure.persistence.jpa.entity.AddressEntity;

@Mapper(componentModel = "spring")
public interface AddressJpaMapper {
    Address toDomain(AddressEntity addressEntity);

    AddressEntity toEntity(Address address);
}
