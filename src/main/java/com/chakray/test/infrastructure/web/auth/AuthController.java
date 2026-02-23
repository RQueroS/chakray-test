package com.chakray.test.infrastructure.web.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Authentication")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login successful");
    }
}
