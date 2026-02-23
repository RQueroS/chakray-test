package com.chakray.test.infrastructure.seed;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.chakray.test.domain.ports.out.PasswordEncoderPort;
import com.chakray.test.infrastructure.persistence.jpa.repository.UserJpaRepository;

@SpringBootTest
@ActiveProfiles("test")
public class UserDataSeederTest {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private PasswordEncoderPort passwordEncoderPort;

    @Test
    @DisplayName("Should seed user data with correct encryption and relational integrity")
    public void testUserDataSeeding() {
        var users = userJpaRepository.findAll();
        assertFalse(users.isEmpty(), "User data should be seeded");

        var user = users.stream().findFirst().orElse(users.get(0));
        assertNotNull(user.getName());
        assertTrue(passwordEncoderPort.matches("Password-123", user.getPassword()),
                "Password in DB must be the encoded version of 'Password-123'");

        long totalUsers = userJpaRepository.count();
        assertTrue(totalUsers > 0);
    }
}
