package com.chakray.test.infrastructure.web.dto;

import org.mapstruct.Mapper;

import com.chakray.test.domain.Country;

@Mapper(componentModel = "spring")
public interface CountryDtoMapper {
    CountryResDto toDto(Country country);
}
