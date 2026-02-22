package com.chakray.test.infrastructure.web.address;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chakray.test.domain.Address;
import com.chakray.test.domain.ports.in.RetrieveAddressUseCase;
import com.chakray.test.domain.ports.in.SaveAddressUseCase;
import com.chakray.test.infrastructure.web.address.dto.AddressResDto;
import com.chakray.test.infrastructure.web.address.dto.CreateAddressReqDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Address For User")
@RestController
@RequestMapping("/users/{userId}/addresses")
@RequiredArgsConstructor
public class AddressController {
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    private final AddressDtoMapper addressDtoMapper;
    private final RetrieveAddressUseCase retrieveAddressUseCase;
    private final SaveAddressUseCase saveAddressUseCase;

    @Operation(summary = "Get all addresses for a user")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of addresses for the user")
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    @GetMapping
    public ResponseEntity<List<AddressResDto>> getUserAddresses(
            @Schema(description = "The UUID of the user", example = "537b5b2f-caef-4483-b165-8c8b8c77c80b") @PathVariable UUID userId) {
        logger.info("Received request to get addresses for user ID: {}", userId);
        List<Address> addresses = retrieveAddressUseCase.getAllAddressesByUserId(userId);

        logger.info("Successfully retrieved {} addresses for user ID: {}", addresses.size(), userId);
        List<AddressResDto> res = addresses.stream().map(addressDtoMapper::toDto).toList();

        logger.debug("Mapping Address entities to AddressResDto for user ID: {}", userId);
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Add a new address for a user")
    @ApiResponse(responseCode = "200", description = "Successfully added a new address for the user")
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    @ApiResponse(responseCode = "404", description = "Country code not found", content = @Content)
    @PostMapping
    public ResponseEntity<AddressResDto> addUserAddress(
            @Schema(description = "The UUID of the user", example = "537b5b2f-caef-4483-b165-8c8b8c77c80b") @PathVariable UUID userId,
            @Valid @RequestBody CreateAddressReqDto addressReqDto) {

        logger.info("Received request to add address for user ID: {}", userId);
        Address domainAddress = addressDtoMapper.toEntity(addressReqDto);

        logger.debug("Mapped CreateAddressReqDto to Address entity for user ID: {}", userId);
        Address savedAddress = saveAddressUseCase.saveAddress(userId, domainAddress);

        logger.info("Successfully saved address with ID: {} for user ID: {}", savedAddress.getId(), userId);
        AddressResDto res = addressDtoMapper.toDto(savedAddress);

        logger.debug("Mapped saved Address entity to AddressResDto for user ID: {}", userId);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{addressId}")
    public ResponseEntity<String> updateUserAddress(
            @Schema(description = "The UUID of the user", example = "537b5b2f-caef-4483-b165-8c8b8c77c80b") @PathVariable UUID userId,
            @Schema(description = "The ID of the address", example = "1") @PathVariable Long addressId) {
        return ResponseEntity.ok("Update address with ID: " + addressId + " for user ID: " + userId);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteUserAddress(
            @Schema(description = "The UUID of the user", example = "537b5b2f-caef-4483-b165-8c8b8c77c80b") @PathVariable UUID userId,
            @Schema(description = "The ID of the address", example = "1") @PathVariable Long addressId) {
        return ResponseEntity.ok("Delete address with ID: " + addressId + " for user ID: " + userId);
    }
}
