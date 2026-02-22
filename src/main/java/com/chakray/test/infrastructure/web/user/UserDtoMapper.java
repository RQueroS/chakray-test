package com.chakray.test.infrastructure.web.user;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Value;

import com.chakray.test.domain.Address;
import com.chakray.test.domain.User;
import com.chakray.test.infrastructure.web.address.dto.AddressResDto;
import com.chakray.test.infrastructure.web.user.dto.CreateUserReqDto;
import com.chakray.test.infrastructure.web.user.dto.UserResDto;

@Mapper(componentModel = "spring")
public abstract class UserDtoMapper {
    @Value("${app.timezone}")
    protected String zoneId;

    public abstract UserResDto toDto(User user);

    @Mapping(source = "country.code", target = "countryCode")
    public abstract AddressResDto toDto(Address address);

    public String utcToLocalZone(Instant instant) {
        if (instant == null)
            return null;

        ZonedDateTime utcZonedDateTime = instant.atZone(ZoneId.of(zoneId));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return utcZonedDateTime.format(formatter);
    }

    public abstract User toDomain(CreateUserReqDto createUserReqDto);
}
