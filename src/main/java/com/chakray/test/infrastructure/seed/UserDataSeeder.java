package com.chakray.test.infrastructure.seed;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.chakray.test.infrastructure.persistence.jpa.entity.AddressEntity;
import com.chakray.test.infrastructure.persistence.jpa.entity.CountryEntity;
import com.chakray.test.infrastructure.persistence.jpa.entity.UserEntity;
import com.chakray.test.infrastructure.persistence.jpa.repository.CountryJpaRepository;
import com.chakray.test.infrastructure.persistence.jpa.repository.UserJpaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Order(2)
@Component
@RequiredArgsConstructor
public class UserDataSeeder implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(UserDataSeeder.class);
    private final UserJpaRepository userJpaRepository;
    private final CountryJpaRepository countryDataSeeder;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        if (userJpaRepository.count() == 0) {
            logger.info("Seeding user data...");

            logger.debug("Attempting to load users.json from classpath...");
            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("data/users.json");

            if (inputStream == null) {
                logger.error("users.json not found in classpath");
                throw new IllegalStateException("users.json not found in classpath");
            }

            logger.debug("users.json found. Parsing JSON data...");
            var typeRef = new TypeReference<List<UserEntity>>() {
            };
            List<UserEntity> users = objectMapper.readValue(
                    inputStream, typeRef);

            logger.info("Parsed " + users.size() + " users. Processing addresses and saving to database...");
            logger.debug("Processing each user and their addresses to set country references...");
            for (UserEntity user : users) {
                if (user.getAddresses() != null) {
                    for (AddressEntity address : user.getAddresses()) {
                        String countryCode = address.getCountry().getCode();
                        CountryEntity country = countryDataSeeder.findByCode(countryCode)
                                .orElseThrow(() -> {
                                    logger.error("Country with code {} not found for user {}", countryCode,
                                            user.getEmail());
                                    return new IllegalStateException("Country not found: " + countryCode);
                                });

                        address.setCountry(country);
                        address.setUser(user);
                    }
                }
                userJpaRepository.save(user);
            }

            logger.info("User data seeding completed.");
        }
    }
}
