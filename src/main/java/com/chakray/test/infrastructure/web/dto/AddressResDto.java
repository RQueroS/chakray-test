package com.chakray.test.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressResDto {
    private Long id;
    private String name;
    private String street;
    private String countryCode;
}
