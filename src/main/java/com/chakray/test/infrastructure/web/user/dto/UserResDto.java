package com.chakray.test.infrastructure.web.user.dto;

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
    private String taxId;
    private String createdAt;
    private List<AddressResDto> addresses;
}
