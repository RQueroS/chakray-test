package com.chakray.test.infrastructure.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chakray.test.domain.User;
import com.chakray.test.domain.ports.in.RetrieveUserUseCase;
import com.chakray.test.infrastructure.web.dto.UserDtoMapper;
import com.chakray.test.infrastructure.web.dto.UserReqParamsDto;
import com.chakray.test.infrastructure.web.dto.UserResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "User")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserDtoMapper userDtoMapper;
    private final RetrieveUserUseCase retrieveUserUseCase;

    @Operation(summary = "Get all users with optional sorting")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users")
    @GetMapping
    public ResponseEntity<List<UserResDto>> getUsers(@Valid @ParameterObject UserReqParamsDto reqParams) {
        logger.info("Received request to get all users");
        logger.debug("Calling retrieveUserUseCase to get users");
        List<User> users = retrieveUserUseCase.getUsers(reqParams.getSortedBy(), reqParams.getOrderBy());

        logger.debug("Mapping User entities to UserResDto");
        List<UserResDto> res = users.stream().map(userDtoMapper::toDto).toList();

        logger.info("Successfully retrieved {} users", res.size());
        return ResponseEntity.ok(res);
    }
}
