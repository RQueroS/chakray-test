package com.chakray.test.infrastructure.persistence.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.chakray.test.domain.User;
import com.chakray.test.infrastructure.persistence.jpa.entity.UserEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserJpaMapper {
    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    void updateEntityFromDto(User source, @MappingTarget UserEntity target);
}
