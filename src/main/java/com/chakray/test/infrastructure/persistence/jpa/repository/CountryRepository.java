package com.chakray.test.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chakray.test.infrastructure.persistence.jpa.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

}
