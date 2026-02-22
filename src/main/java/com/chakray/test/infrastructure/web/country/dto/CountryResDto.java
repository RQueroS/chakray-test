package com.chakray.test.infrastructure.web.country.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryResDto {
    private String code;
    private String name;
}
