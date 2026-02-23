package com.chakray.test.domain;

import lombok.Data;
import lombok.ToString;

@Data
public class LoginResponse {
    @ToString.Exclude
    private String token;
}
