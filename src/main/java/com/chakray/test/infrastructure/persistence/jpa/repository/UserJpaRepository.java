package com.chakray.test.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chakray.test.infrastructure.persistence.jpa.entity.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

}
