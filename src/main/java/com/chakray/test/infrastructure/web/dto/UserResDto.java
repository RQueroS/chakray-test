package com.chakray.test.infrastructure.web.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResDto {
    private String id;
    private String email;
    private String name;
    private String phone;
    private String password;
    private String taxId;
    private Instant createdAt;
    private List<AddressResDto> addresses;
}
