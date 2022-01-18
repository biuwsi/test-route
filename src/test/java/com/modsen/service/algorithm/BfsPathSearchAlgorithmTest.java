package com.modsen.service.algorithm;

import com.modsen.exception.BusinessException;
import com.modsen.exception.ErrorCode;
import com.modsen.service.loader.Country;
import com.modsen.service.loader.CountryLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class BfsPathSearchAlgorithmTest {
    @Mock
    private CountryLoader loader;
    @InjectMocks
    private BfsPathSearchAlgorithm algorithm;

    @Test
    void unknownCountryCodeTest() {
        Mockito.when(loader.getCountries()).thenReturn(List.of());

        final BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> algorithm.findPath("from", "to"));
        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.UNKNOWN_COUNTRY_CODE);
    }

    @Test
    void noRouteTest() {
        Mockito.when(loader.getCountries()).thenReturn(List.of(
                Country.builder()
                        .name("from")
                        .borders(List.of())
                        .build(),
                Country.builder()
                        .name("to")
                        .borders(List.of())
                        .build()
        ));

        Assertions.assertEquals(algorithm.findPath("from", "to"), List.of());
    }
}