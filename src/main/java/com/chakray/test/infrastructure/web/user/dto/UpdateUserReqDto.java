package com.chakray.test.infrastructure.web.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserReqDto {
    @Schema(description = "User's email address", example = "user@example.com")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "User's full name", example = "John Doe")
    private String name;

    @Schema(description = "User's phone number", example = "+521234567890")
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
    @Size(min = 12, message = "Password must be at least 12 characters long")
    @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter")
    @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter")
    @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit")
    @Pattern(regexp = ".*[!@#$%^&*()].*", message = "Password must contain at least one special character (!@#$%^&*())")
    @Pattern(regexp = "^[^\\s]+$", message = "Password must not contain whitespace")
    private String password;
}
