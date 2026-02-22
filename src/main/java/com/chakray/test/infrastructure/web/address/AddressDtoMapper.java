package com.chakray.test.infrastructure.web.address;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.chakray.test.domain.Address;
import com.chakray.test.infrastructure.web.address.dto.AddressResDto;

@Mapper(componentModel = "spring")
public interface AddressDtoMapper {
    @Mapping(source = "country.code", target = "countryCode")
    AddressResDto toDto(Address address);
}
