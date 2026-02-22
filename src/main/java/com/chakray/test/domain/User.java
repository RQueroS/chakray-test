package com.chakray.test.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class User {
    private String id;
    private String email;
    private String name;
    private String phone;
    private String password;
    private String taxId;
    private Instant createdAt;
    private List<Address> addresses = new ArrayList<>();
}
