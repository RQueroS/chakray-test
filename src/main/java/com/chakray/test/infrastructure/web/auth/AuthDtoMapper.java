package com.chakray.test.infrastructure.web.auth;

import org.mapstruct.Mapper;

import com.chakray.test.domain.LoginRequest;
import com.chakray.test.domain.LoginResponse;
import com.chakray.test.infrastructure.web.auth.dto.LoginReqDto;
import com.chakray.test.infrastructure.web.auth.dto.LoginResDto;

@Mapper(componentModel = "spring")
public interface AuthDtoMapper {
    LoginRequest toLoginRequest(LoginReqDto loginReqDto);

    LoginResDto toLoginResDto(LoginResponse loginResponse);
}
