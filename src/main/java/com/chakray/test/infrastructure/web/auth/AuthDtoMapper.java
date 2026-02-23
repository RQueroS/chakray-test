package com.chakray.test.infrastructure.web.auth;

import org.mapstruct.Mapper;

import com.chakray.test.domain.LoginRequest;
import com.chakray.test.infrastructure.web.auth.dto.LoginReqDto;

@Mapper(componentModel = "spring")
public interface AuthDtoMapper {
    LoginRequest toLoginRequest(LoginReqDto loginReqDto);
}
