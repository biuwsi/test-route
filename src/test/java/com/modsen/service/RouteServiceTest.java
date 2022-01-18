package com.modsen.service;

import com.modsen.controller.api.RouteResponse;
import com.modsen.exception.BusinessException;
import com.modsen.exception.ErrorCode;
import com.modsen.service.algorithm.PathSearchAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @Mock
    private PathSearchAlgorithm algorithm;

    @InjectMocks
    private RouteService routeService;

    @Test
    void routeFoundTest() {
        final List<String> path = List.of("FROM", "TO");
        Mockito.when(algorithm.findPath(any(), any())).thenReturn(path);

        final RouteResponse route = routeService.findRoute("FROM", "TO");

        Assertions.assertEquals(route.getRoute(), path);
        Mockito.verify(algorithm, Mockito.only()).findPath(any(), any());
    }

    @Test
    void emptyListReturnedTest() {
        Mockito.when(algorithm.findPath(any(), any())).thenReturn(List.of());

        final BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> routeService.findRoute("from", "to"));

        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.PATH_NOT_FOUND);
        Mockito.verify(algorithm, Mockito.only()).findPath(any(), any());
    }
}