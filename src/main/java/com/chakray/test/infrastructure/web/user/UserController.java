package com.chakray.test.infrastructure.web.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chakray.test.domain.User;
import com.chakray.test.domain.ports.in.DeleteUserUseCase;
import com.chakray.test.domain.ports.in.RetrieveUserUseCase;
import com.chakray.test.domain.ports.in.SaveUserUseCase;
import com.chakray.test.infrastructure.web.user.dto.CreateUserReqDto;
import com.chakray.test.infrastructure.web.user.dto.UpdateUserReqDto;
import com.chakray.test.infrastructure.web.user.dto.UserReqParamsDto;
import com.chakray.test.infrastructure.web.user.dto.UserResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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
    private final DeleteUserUseCase deleteUserUseCase;

    private static final String USER_ID_PATH_VARIABLE_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

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
    @ApiResponse(responseCode = "404", description = "Country not found for one of the user's addresses", content = @Content)
    @ApiResponse(responseCode = "409", description = "User with the same tax ID already exists", content = @Content)
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

    @Operation(summary = "Delete a user by ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted the user")
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @Schema(description = "The UUID of the user to delete", example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable @Pattern(regexp = USER_ID_PATH_VARIABLE_REGEX, message = "Invalid UUID format") String userId) {
        logger.info("Received request to delete user with ID: {}", userId);
        deleteUserUseCase.deleteUser(userId);

        logger.info("Successfully deleted user with ID: {}", userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update a user by ID")
    @ApiResponse(responseCode = "200", description = "Successfully updated the user")
    @ApiResponse(responseCode = "404", description = "User not found or country not found for one of the user's addresses", content = @Content)
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResDto> updateUser(
            @Schema(description = "The UUID of the user to update", example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable @Pattern(regexp = USER_ID_PATH_VARIABLE_REGEX, message = "Invalid UUID format") String userId,
            @Valid @RequestBody UpdateUserReqDto body) {
        logger.info("Received request to update user with ID: {}", userId);
        User domainUser = userDtoMapper.toDomain(body);

        logger.debug("Calling saveUserUseCase to update user with ID: {}", userId);
        User user = saveUserUseCase.updateUser(userId, domainUser);

        logger.info("Successfully updated user with ID: {}", userId);
        UserResDto res = userDtoMapper.toDto(user);

        logger.info("Returning response for updated user with ID: {}", res.getId());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{userId}/addresses")
    public ResponseEntity<String> getUserAddresses(@PathVariable String userId) {
        return ResponseEntity.ok("User addresses for user ID: " + userId);
    }

    @PostMapping("/{userId}/addresses")
    public ResponseEntity<String> addUserAddress(@PathVariable String userId) {
        return ResponseEntity.ok("Add address for user ID: " + userId);
    }

    @PatchMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<String> updateUserAddress(@PathVariable String userId, @PathVariable String addressId) {
        return ResponseEntity.ok("Update address with ID: " + addressId + " for user ID: " + userId);
    }

    @DeleteMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<String> deleteUserAddress(@PathVariable String userId, @PathVariable String addressId) {
        return ResponseEntity.ok("Delete address with ID: " + addressId + " for user ID: " + userId);
    }
}