package com.chakray.test.infrastructure.web.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chakray.test.domain.LoginRequest;
import com.chakray.test.domain.ports.in.AuthUseCase;
import com.chakray.test.infrastructure.web.auth.dto.LoginReqDto;
import com.chakray.test.infrastructure.web.auth.dto.LoginResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Authentication")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthDtoMapper authDtoMapper;
    private final AuthUseCase authUseCase;

    @Operation(summary = "Authenticate user and return a JWT token")
    @ApiResponse(responseCode = "200", description = "Successful login")
    @ApiResponse(responseCode = "400", description = "Username or password is incorrect", content = @Content)
    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginReqDto loginReqDto) {
        logger.info("Login request received for username: {}", loginReqDto.getUsername());
        LoginRequest loginRequest = authDtoMapper.toLoginRequest(loginReqDto);

        logger.debug("LoginRequest mapped: {}", loginRequest);
        LoginResDto loginResDto = authDtoMapper.toLoginResDto(authUseCase.login(loginRequest));

        logger.info("Login successful for username: {}", loginReqDto.getUsername());
        return ResponseEntity.ok(loginResDto);
    }
}
