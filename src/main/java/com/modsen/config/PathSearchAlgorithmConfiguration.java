package com.modsen.config;

import com.modsen.service.algorithm.BfsPathSearchAlgorithm;
import com.modsen.service.algorithm.PathSearchAlgorithm;
import com.modsen.service.loader.CountryLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class PathSearchAlgorithmConfiguration {
    @Configuration
    @ConditionalOnProperty(name = "route.algorithm", havingValue = "bts", matchIfMissing = true)
    public static class BtsPathSearchAlgorithmConfiguration {
        @Bean
        public PathSearchAlgorithm pathSearchAlgorithm(CountryLoader countryLoader) {
            return new BfsPathSearchAlgorithm(countryLoader);
        }
    }
}
