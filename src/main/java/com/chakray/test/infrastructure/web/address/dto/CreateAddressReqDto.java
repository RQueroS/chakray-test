package com.chakray.test.infrastructure.web.address.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAddressReqDto {
    @Schema(description = "Name of the address", example = "Home")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Street address", example = "St. 1234, City, State")
    @NotBlank(message = "Street is required")
    private String street;

    @Schema(description = "Country code", example = "MEX")
    @NotBlank(message = "Country code is required")
    @Size(min = 3, max = 3, message = "Country code must be exactly 3 characters")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Country code must be 3 uppercase letters")
    private String countryCode;
}
