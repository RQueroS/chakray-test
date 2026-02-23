package com.chakray.test.infrastructure.web.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
public class LoginResDto {
    @ToString.Exclude
    private String token;
}
