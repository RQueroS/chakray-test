package com.chakray.test.infrastructure.web.country.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryResDto {
    @Schema(description = "Country code", example = "MEX")
    private String code;

    @Schema(description = "Country name", example = "Mexico")
    private String name;
}
