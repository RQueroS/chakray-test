package com.chakray.test.infrastructure.web.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginReqDto {
    @Schema(description = "Username of the user (Tax ID)", example = "ABCD123456EFG")
    @NotBlank(message = "Username is required")
    private String username;

    @Schema(description = "Password of the user", example = "P@ssw0rd1234")
    @NotBlank(message = "Password is required")
    private String password;
}
