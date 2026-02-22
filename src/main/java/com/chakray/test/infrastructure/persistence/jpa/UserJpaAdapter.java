package com.chakray.test.infrastructure.persistence.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.chakray.test.domain.User;
import com.chakray.test.domain.ports.out.UserRepositoryPort;
import com.chakray.test.infrastructure.persistence.jpa.entity.UserEntity;
import com.chakray.test.infrastructure.persistence.jpa.repository.UserJpaRepository;

import jakarta.persistence.criteria.Path;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepositoryPort {
    private static final Logger logger = LoggerFactory.getLogger(UserJpaAdapter.class);
    private final UserJpaRepository userJpaRepository;
    private final UserJpaMapper userJpaMapper;

    @Override
    public List<User> findAllUsers(String sortedBy, String orderBy, String filter) {
        logger.debug("Fetching all users with sorting and filtering: sortedBy={}, orderBy={}, filter={}",
                sortedBy, orderBy, filter);
        Sort.Direction direction = Sort.Direction.fromString(orderBy);

        logger.debug("Sorting by: {} {}", direction, sortedBy);
        Sort sort = Sort.by(direction, sortedBy);

        logger.debug("Building specification for filter: {}", filter);
        Specification<UserEntity> spec = buildSpecification(filter);

        logger.debug("Fetching users from the database with specification and sorting");
        List<UserEntity> users = (spec != null)
                ? userJpaRepository.findAll(spec, sort)
                : userJpaRepository.findAll(sort);

        logger.debug("Mapping UserEntity to User domain objects");
        List<User> domainUsers = users.stream()
                .map(userJpaMapper::toDomain)
                .toList();

        logger.debug("Successfully mapped {} UserEntity to User", domainUsers.size());
        return domainUsers;
    }

    private Specification<UserEntity> buildSpecification(String filter) {
        if (filter == null || filter.isBlank())
            return null;

        String[] parts = filter.split("\\+", 3);
        if (parts.length != 3)
            return null;

        // eq -> equals,
        // co -> contains,
        // sw -> starts with,
        // ew -> ends with
        String field = parts[0]; // id, email, name, etc.
        String operator = parts[1]; // eq, co, sw, ew
        String value = parts[2]; // value to compare against

        return (root, query, criteriaBuilder) -> {
            Path<String> path = root.get(field);

            switch (operator.toLowerCase()) {
                case "eq":
                    return criteriaBuilder.equal(path, value);
                case "co":
                    return criteriaBuilder.like(path, "%" + value + "%");
                case "sw":
                    return criteriaBuilder.like(path, value + "%");
                case "ew":
                    return criteriaBuilder.like(path, "%" + value);
                default:
                    return null;
            }
        };
    }
}
