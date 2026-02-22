package com.chakray.test.infrastructure.web.user.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserReqDto {
    @Schema(description = "User's email address", example = "user@example.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "User's full name", example = "John Doe")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "User's phone number", example = "+521234567890")
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\+\\d{1,3}\\d{10}$", message = "Phone number must be in the format +[country_code][number], e.g., +521234567890")
    private String phone;

    /**
     * OWASP Rules
     * - Minimum length of 12 characters
     * - At least one uppercase letter
     * - At least one lowercase letter
     * - At least one digit
     * - At least one special character (!@#$%^&*())
     * - No whitespace allowed
     */
    @Schema(description = "User's password", example = "P@ssw0rd1234")
    @NotBlank(message = "Password is required")
    @Size(min = 12, message = "Password must be at least 12 characters long")
    @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter")
    @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter")
    @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit")
    @Pattern(regexp = ".*[!@#$%^&*()].*", message = "Password must contain at least one special character (!@#$%^&*())")
    @Pattern(regexp = "^[^\\s]+$", message = "Password must not contain whitespace")
    private String password;

    @Schema(description = "User's tax ID", example = "ABCD123456EFG")
    @NotBlank(message = "Tax ID is required")
    @Size(min = 13, max = 13, message = "Tax ID must be exactly 13 characters long")
    @Pattern(regexp = "^[A-Z&Ñ]{4}\\d{6}[A-Z0-9]{3}$", message = "Tax ID must follow the format: 4 letters (A-Z, &, Ñ), 6 digits, and 3 alphanumeric characters")
    private String taxId;

    @Valid
    private List<CreateAddressReqDto> addresses;
}
