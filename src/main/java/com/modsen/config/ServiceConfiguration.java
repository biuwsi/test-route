package com.modsen.config;

import com.modsen.service.RouteService;
import com.modsen.service.algorithm.PathSearchAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public RouteService routeService(PathSearchAlgorithm algorithm) {
        return new RouteService(algorithm);
    }
}
