package com.modsen.service;

import com.modsen.controller.api.RouteResponse;
import com.modsen.exception.BusinessException;
import com.modsen.exception.ErrorCode;
import com.modsen.service.algorithm.PathSearchAlgorithm;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RouteService {
    private final PathSearchAlgorithm algorithm;

    public RouteResponse findRoute(String from, String to) {
        final List<String> path = algorithm.findPath(from, to);

        if (path.isEmpty()) {
            throw new BusinessException(ErrorCode.PATH_NOT_FOUND);
        }

        return RouteResponse.builder()
                .route(path)
                .build();
    }
}
