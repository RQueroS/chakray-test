package com.chakray.test.infrastructure.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Country")
@RestController
@RequestMapping("/countries")
public class CountryController {
    @GetMapping
    public ResponseEntity<String> getCountries() {
        return ResponseEntity.ok("List of countries");
    }
}
