package com.chakray.test.infrastructure.persistence.jpa;

import org.mapstruct.Mapper;

import com.chakray.test.domain.Country;
import com.chakray.test.infrastructure.persistence.jpa.entity.CountryEntity;

@Mapper(componentModel = "spring")
public interface CountryJpaMapper {
    CountryEntity toEntity(Country country);

    Country toDomain(CountryEntity countryEntity);
}
