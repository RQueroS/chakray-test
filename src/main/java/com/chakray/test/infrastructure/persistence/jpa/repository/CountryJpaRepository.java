package com.chakray.test.infrastructure.persistence.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chakray.test.infrastructure.persistence.jpa.entity.CountryEntity;

public interface CountryJpaRepository extends JpaRepository<CountryEntity, Long> {
    Optional<CountryEntity> findByCode(String code);
}
