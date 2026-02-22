package com.chakray.test.infrastructure.web.country;

import org.mapstruct.Mapper;

import com.chakray.test.domain.Country;
import com.chakray.test.infrastructure.web.country.dto.CountryResDto;

@Mapper(componentModel = "spring")
public interface CountryDtoMapper {
    CountryResDto toDto(Country country);
}
