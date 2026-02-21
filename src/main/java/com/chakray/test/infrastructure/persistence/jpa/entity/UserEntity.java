package com.chakray.test.infrastructure.persistence.jpa.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", unique = true, nullable = false, length = 14)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "tax_id", unique = true, nullable = false, length = 13)
    private String taxId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Instant createdAt;
}