package com.chakray.test.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.chakray.test.domain.Country;

public interface CountryRepositoryPort {
    List<Country> findAllCountries();

    Optional<Country> findCountryByCode(String code);
}
