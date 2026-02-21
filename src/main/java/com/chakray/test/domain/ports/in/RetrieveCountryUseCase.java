package com.chakray.test.domain.ports.in;

import java.util.List;

import com.chakray.test.domain.Country;

public interface RetrieveCountryUseCase {
    List<Country> getCountries();
}
