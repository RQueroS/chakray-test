package com.chakray.test.infrastructure.web.address;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.chakray.test.domain.Address;
import com.chakray.test.infrastructure.web.address.dto.AddressResDto;
import com.chakray.test.infrastructure.web.address.dto.CreateAddressReqDto;
import com.chakray.test.infrastructure.web.address.dto.UpdateAddressReqDto;

@Mapper(componentModel = "spring")
public interface AddressDtoMapper {
    @Mapping(source = "country.code", target = "countryCode")
    AddressResDto toDto(Address address);

    @Mapping(source = "countryCode", target = "country.code")
    Address toDomain(CreateAddressReqDto addressReqDto);

    @Mapping(source = "countryCode", target = "country.code")
    Address toDomain(UpdateAddressReqDto addressReqDto);
}
