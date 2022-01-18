package com.modsen.service.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ResourceCountryLoader implements CountryLoader {
    private static final TypeReference<List<CountryModel>> TYPE_REFERENCE = new TypeReference<>() {
    };

    private final ObjectMapper objectMapper;
    private final String path;
    private List<Country> countries;

    @Override
    public List<Country> getCountries() {
        return countries;
    }

    public void init() throws IOException {
        log.info("Loading countries from resource file with path: {}", path);
        final File file = ResourceUtils.getFile(path);

        countries = objectMapper.readValue(file, TYPE_REFERENCE)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
        log.info("Loading  completed. Loaded {} countries", countries.size());

    }

    private Country convert(CountryModel model) {
        return Country.builder()
                .name(model.getName())
                .borders(model.getBorders())
                .build();
    }
}
