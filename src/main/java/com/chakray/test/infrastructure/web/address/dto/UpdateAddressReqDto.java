package com.chakray.test.infrastructure.web.address.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateAddressReqDto {
    @Schema(description = "Name of the address", example = "Home")
    private String name;

    @Schema(description = "Street address", example = "St. 1234, City, State")
    private String street;

    @Schema(description = "Country code", example = "MEX")
    @Size(min = 3, max = 3, message = "Country code must be exactly 3 characters")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Country code must be 3 uppercase letters")
    private String countryCode;
}
