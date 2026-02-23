package com.chakray.test.infrastructure.persistence.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chakray.test.infrastructure.persistence.jpa.entity.AddressEntity;

import jakarta.transaction.Transactional;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByUserId(UUID userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM AddressEntity a WHERE a.id = :addressId AND a.user.id = :userId")
    void deleteByIdAndUserId(@Param("addressId") Long addressId, @Param("userId") UUID userId);

    Optional<AddressEntity> findByIdAndUserId(Long addressId, UUID userId);
}
