package com.chakray.test.domain;

import lombok.Data;

@Data
public class Address {
    private Long id;
    private String name;
    private String street;
    private Country country;
}
