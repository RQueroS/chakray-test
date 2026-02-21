package com.chakray.test.infrastructure.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chakray.test.domain.Country;
import com.chakray.test.domain.ports.in.RetrieveCountryUseCase;
import com.chakray.test.infrastructure.web.dto.CountryDtoMapper;
import com.chakray.test.infrastructure.web.dto.CountryResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Country")
@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);
    private final CountryDtoMapper countryDtoMapper;
    private final RetrieveCountryUseCase retrieveCountryUseCase;

    @Operation(summary = "Get all countries")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of countries")
    @GetMapping
    public ResponseEntity<List<CountryResDto>> getCountries() {
        logger.info("Received request to get all countries");
        logger.debug("Calling retrieveCountryUseCase to get countries");
        List<Country> countries = retrieveCountryUseCase.getCountries();

        logger.debug("Mapping Country entities to CountryResDto");
        List<CountryResDto> res = countries.stream().map(countryDtoMapper::toDto).toList();

        logger.info("Successfully retrieved {} countries", res.size());
        return ResponseEntity.ok(res);
    }
}
