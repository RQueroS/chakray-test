package com.chakray.test.infrastructure.persistence.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.chakray.test.domain.Country;
import com.chakray.test.domain.ports.out.CountryRepositoryPort;
import com.chakray.test.infrastructure.persistence.jpa.entity.CountryEntity;
import com.chakray.test.infrastructure.persistence.jpa.repository.CountryJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CountryJpaAdapter implements CountryRepositoryPort {
    private static final Logger logger = LoggerFactory.getLogger(CountryJpaAdapter.class);
    private final CountryJpaRepository countryJpaRepository;
    private final CountryJpaMapper countryJpaMapper;

    @Override
    public List<Country> findAllCountries() {
        logger.debug("Fetching all countries from the database");
        List<CountryEntity> entities = countryJpaRepository.findAll();

        logger.debug("Mapping CountryEntity to Country domain objects");
        List<Country> countries = entities.stream()
                .map(countryJpaMapper::toDomain)
                .toList();

        logger.debug("Successfully mapped {} CountryEntity to Country", countries.size());
        return countries;
    }
}
