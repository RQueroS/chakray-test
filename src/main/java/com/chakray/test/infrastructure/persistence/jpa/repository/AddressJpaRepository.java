package com.chakray.test.infrastructure.persistence.jpa.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chakray.test.infrastructure.persistence.jpa.entity.AddressEntity;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByUserId(UUID userId);
}
