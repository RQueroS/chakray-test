package com.chakray.test.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chakray.test.infrastructure.persistence.jpa.entity.CountryEntity;

public interface CountryJpaRepository extends JpaRepository<CountryEntity, Long> {

}
