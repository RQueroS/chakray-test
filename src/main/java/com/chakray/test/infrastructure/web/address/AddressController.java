package com.chakray.test.infrastructure.web.address;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Address For User")
@RestController
@RequestMapping("/users/{userId}/addresses")
public class AddressController {
    @GetMapping
    public ResponseEntity<String> getUserAddresses(
            @Schema(description = "The UUID of the user", example = "537b5b2f-caef-4483-b165-8c8b8c77c80b") @PathVariable UUID userId) {
        return ResponseEntity.ok("User addresses for user ID: " + userId);
    }

    @PostMapping
    public ResponseEntity<String> addUserAddress(
            @Schema(description = "The UUID of the user", example = "537b5b2f-caef-4483-b165-8c8b8c77c80b") @PathVariable UUID userId) {
        return ResponseEntity.ok("Add address for user ID: " + userId);
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
