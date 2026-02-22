package com.chakray.test.infrastructure.web.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.chakray.test.domain.Address;
import com.chakray.test.domain.User;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserResDto toDto(User user);

    @Mapping(source = "country.code", target = "countryCode")
    AddressResDto toDto(Address address);
}
