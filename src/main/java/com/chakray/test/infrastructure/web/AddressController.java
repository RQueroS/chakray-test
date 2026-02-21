package com.chakray.test.infrastructure.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Address")
@RestController
@RequestMapping("/addresses")
public class AddressController {
    @GetMapping
    public ResponseEntity<String> getAddresses() {
        return ResponseEntity.ok("List of addresses");
    }
}
