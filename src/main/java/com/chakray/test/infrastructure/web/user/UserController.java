package com.chakray.test.infrastructure.web.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chakray.test.domain.User;
import com.chakray.test.domain.ports.in.RetrieveUserUseCase;
import com.chakray.test.domain.ports.in.SaveUserUseCase;
import com.chakray.test.infrastructure.web.user.dto.CreateUserReqDto;
import com.chakray.test.infrastructure.web.user.dto.UserReqParamsDto;
import com.chakray.test.infrastructure.web.user.dto.UserResDto;

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
    private final SaveUserUseCase saveUserUseCase;

    @Operation(summary = "Get all users with optional sorting")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users")
    @GetMapping
    public ResponseEntity<List<UserResDto>> getUsers(@Valid @ParameterObject UserReqParamsDto reqParams) {
        logger.info("Received request to get all users");
        logger.debug("Calling retrieveUserUseCase to get users");
        List<User> users = retrieveUserUseCase.getUsers(reqParams.getSortedBy(), reqParams.getOrderBy(),
                reqParams.getFilter());

        logger.debug("Mapping User entities to UserResDto");
        List<UserResDto> res = users.stream().map(userDtoMapper::toDto).toList();

        logger.info("Successfully retrieved {} users", res.size());
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Create a new user")
    @ApiResponse(responseCode = "200", description = "Successfully created a new user")
    @PostMapping
    public ResponseEntity<UserResDto> createUser(@Valid @RequestBody CreateUserReqDto body) {
        logger.info("Received request to create a new user with name: {}", body.getName());
        User domainUser = userDtoMapper.toDomain(body);

        logger.debug("Calling saveUserUseCase to save the new user");
        User user = saveUserUseCase.saveUser(domainUser);

        logger.info("Successfully created user with ID: {}", user.getId());
        UserResDto res = userDtoMapper.toDto(user);

        logger.info("Returning response for created user with ID: {}", res.getId());
        return ResponseEntity.ok(res);
    }
}
