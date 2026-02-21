package com.chakray.test.infrastructure.seed;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.chakray.test.infrastructure.persistence.jpa.entity.CountryEntity;
import com.chakray.test.infrastructure.persistence.jpa.repository.CountryJpaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Order(1)
@Component
@RequiredArgsConstructor
public class CountryDataSeeder implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CountryDataSeeder.class);
    private final CountryJpaRepository countryRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        if (countryRepository.count() == 0) {
            logger.info("No countries found. Seeding initial country data...");

            logger.debug("Attempting to load countries.json from classpath...");
            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("data/countries.json");

            if (inputStream == null) {
                logger.error("countries.json not found in classpath");
                throw new IllegalStateException("countries.json not found in classpath");
            }

            logger.debug("countries.json found. Parsing JSON data...");
            var typeRef = new TypeReference<List<CountryEntity>>() {
            };
            List<CountryEntity> countries = objectMapper.readValue(
                    inputStream, typeRef);

            logger.debug("Parsed " + countries.size() + " countries. Saving to database...");
            countryRepository.saveAll(countries);

            logger.info("Country data seeding completed.");
        }
    }
}
