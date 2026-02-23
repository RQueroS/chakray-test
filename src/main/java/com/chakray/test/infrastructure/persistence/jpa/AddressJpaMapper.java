package com.chakray.test.infrastructure.persistence.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.chakray.test.domain.Address;
import com.chakray.test.infrastructure.persistence.jpa.entity.AddressEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressJpaMapper {
    Address toDomain(AddressEntity addressEntity);

    AddressEntity toEntity(Address address);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntityFromDto(Address source, @MappingTarget AddressEntity target);
}
