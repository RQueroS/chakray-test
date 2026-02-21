package com.chakray.test.domain;

import lombok.Data;

@Data
public class Country {
    private Long id;
    private String code;
    private String name;

    public boolean isValidCode() {
        return code != null && code.matches("^[A-Z]{3}$");
    }

    public boolean isValidName() {
        return name != null && !name.trim().isEmpty() && name.length() <= 50;
    }
}
