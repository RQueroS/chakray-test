package com.chakray.test.infrastructure.persistence.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.chakray.test.domain.User;
import com.chakray.test.domain.ports.out.UserRepositoryPort;
import com.chakray.test.infrastructure.persistence.jpa.entity.UserEntity;
import com.chakray.test.infrastructure.persistence.jpa.repository.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepositoryPort {
    private static final Logger logger = LoggerFactory.getLogger(UserJpaAdapter.class);
    private final UserJpaRepository userJpaRepository;
    private final UserJpaMapper userJpaMapper;

    @Override
    public List<User> findAllUsers(String sortedBy, String orderBy) {
        logger.debug("Fetching all users from the database");
        List<UserEntity> users = userJpaRepository.findAll(Sort.by(Sort.Direction.fromString(orderBy), sortedBy));

        logger.debug("Mapping UserEntity to User domain objects");
        List<User> domainUsers = users.stream()
                .map(userJpaMapper::toDomain)
                .toList();

        logger.debug("Successfully mapped {} UserEntity to User", domainUsers.size());
        return domainUsers;
    }
}
