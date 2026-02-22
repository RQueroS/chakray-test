package com.chakray.test.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Optional;

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
        List<CountryEntity> countryEntities = countryJpaRepository.findAll();

        logger.debug("Mapping CountryEntity to Country domain objects");
        List<Country> countries = countryEntities.stream()
                .map(countryJpaMapper::toDomain)
                .toList();

        logger.debug("Successfully mapped {} CountryEntity to Country", countries.size());
        return countries;
    }

    @Override
    public Optional<Country> findCountryByCode(String code) {
        logger.debug("Fetching country with code {} from the database", code);
        Optional<CountryEntity> countryEntity = countryJpaRepository.findByCode(code);

        logger.debug("Mapping CountryEntity to Country domain object if present");
        Optional<Country> country = countryEntity.map(countryJpaMapper::toDomain);

        logger.debug("Successfully mapped CountryEntity to Country: {}", country.isPresent());
        return country;
    }
}
