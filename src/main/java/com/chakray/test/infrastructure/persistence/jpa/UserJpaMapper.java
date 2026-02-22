package com.chakray.test.infrastructure.persistence.jpa;

import org.mapstruct.Mapper;

import com.chakray.test.domain.User;
import com.chakray.test.infrastructure.persistence.jpa.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserJpaMapper {
    User toDomain(UserEntity entity);
}
