package com.modsen.service.algorithm;

import com.modsen.exception.BusinessException;
import com.modsen.exception.ErrorCode;
import com.modsen.service.loader.Country;
import com.modsen.service.loader.CountryLoader;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BfsPathSearchAlgorithm implements PathSearchAlgorithm {

    private final CountryLoader countryLoader;

    @Override
    public List<String> findPath(String from, String to) {
        final Map<String, List<String>> countries = countryLoader.getCountries()
                .stream()
                .collect(Collectors.toMap(Country::getName, Country::getBorders));

        if (countries.get(from) == null || countries.get(to) == null) {
            throw new BusinessException(ErrorCode.UNKNOWN_COUNTRY_CODE);
        }

        return findDestination(countries, from, to);
    }

    private List<String> findDestination(Map<String, List<String>> countries, String from, String to) {
        final Set<String> visited = new HashSet<>();
        visited.add(from);

        final Deque<String> deque = new LinkedList<>();
        deque.add(from);
        final Map<String, String> routeMap = new HashMap<>();

        while (!deque.isEmpty()) {
            final String node = deque.removeFirst();
            for (String borderCountry : countries.get(node)) {
                if (visited.contains(borderCountry)) {
                    continue;
                }
                visited.add(borderCountry);
                routeMap.put(borderCountry, node);
                if (Objects.equals(borderCountry, to)) {
                    return extractRoute(routeMap, from, to);
                }
                deque.add(borderCountry);
            }
        }

        return List.of();
    }

    private List<String> extractRoute(Map<String, String> map, String from, String to) {
        String extracted = to;
        final List<String> list = new ArrayList<>();
        list.add(extracted);
        while (!Objects.equals(from, extracted)) {
            extracted = map.get(extracted);
            list.add(extracted);
        }
        Collections.reverse(list);
        return list;
    }
}
