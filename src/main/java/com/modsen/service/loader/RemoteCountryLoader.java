package com.modsen.service.loader;

import com.modsen.service.client.CountriesClient;
import com.modsen.service.client.CountryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class RemoteCountryLoader implements CountryLoader {

    private final CountriesClient countriesClient;
    private List<Country> countries;

    @Override
    public List<Country> getCountries() {
        return countries;
    }

    private void init() {
        log.info("Loading countries from remote resource file");
        countries = countriesClient.getCountries().stream()
                .map(this::convert)
                .collect(Collectors.toList());
        log.info("Loading  completed. Loaded {} countries", countries.size());
    }

    private Country convert(CountryResponse countryResponse) {
        return Country.builder()
                .name(countryResponse.getName())
                .borders(countryResponse.getBorders())
                .build();
    }
}
