package com.chakray.test.domain.ports.out;

import java.util.List;

import com.chakray.test.domain.Country;

public interface CountryRepositoryPort {
    List<Country> findAllCountries();
}
