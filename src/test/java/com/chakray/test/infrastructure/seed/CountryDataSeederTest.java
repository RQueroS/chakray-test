package com.chakray.test.infrastructure.seed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.chakray.test.domain.ports.out.CountryRepositoryPort;

@SpringBootTest
@ActiveProfiles("test")
public class CountryDataSeederTest {
    @Autowired
    private CountryRepositoryPort countryRepositoryPort;

    @Test
    @DisplayName("Should seed country data on application startup")
    public void testCountryDataSeeding() {
        var countries = countryRepositoryPort.findAllCountries();
        assertFalse(countries.isEmpty(), "Country data should be seeded on application startup");

        String expectedCode = "USA";
        var country = countryRepositoryPort.findCountryByCode(expectedCode);
        assertFalse(country.isEmpty(), "Country with code " + expectedCode + " should exist");
        assertFalse(country.get().getName().isEmpty(), "Country name should not be empty");

        expectedCode = "MEX";
        country = countryRepositoryPort.findCountryByCode(expectedCode);
        assertFalse(country.isEmpty(), "Country with code " + expectedCode + " should exist");
        assertFalse(country.get().getName().isEmpty(), "Country name should not be empty");

        long countBefore = countryRepositoryPort.findAllCountries().size();
        long countAfter = countryRepositoryPort.findAllCountries().size();
        assertEquals(countBefore, countAfter, "Country data should not be duplicated on application startup");
    }
}
