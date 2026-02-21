package com.chakray.test.application;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chakray.test.domain.Country;
import com.chakray.test.domain.ports.in.RetrieveCountryUseCase;
import com.chakray.test.domain.ports.out.CountryRepositoryPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CountryService implements RetrieveCountryUseCase {
    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);
    private final CountryRepositoryPort countryRepository;

    @Override
    public List<Country> getCountries() {
        logger.debug("Retrieving countries from CountryRepositoryPort");
        List<Country> countries = countryRepository.findAllCountries();

        logger.debug("Successfully retrieved {} countries", countries.size());
        return countries;
    }
}
