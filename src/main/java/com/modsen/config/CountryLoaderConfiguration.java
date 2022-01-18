package com.modsen.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modsen.service.client.CountriesClient;
import com.modsen.service.loader.RemoteCountryLoader;
import com.modsen.service.loader.ResourceCountryLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Configuration
public class CountryLoaderConfiguration {
    @Configuration
    @EnableFeignClients(clients = CountriesClient.class)
    @ConditionalOnProperty(name = "route.policy", havingValue = "remote")
    public static class RemoteCountryLoaderConfiguration {
        @Bean(initMethod = "init")
        public RemoteCountryLoader remoteCountryLoader(CountriesClient client) {
            return new RemoteCountryLoader(client);
        }

        /**
         * Added to support [text/plain; charset=utf-8] content type from remote
         */
        @Bean
        public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
            final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(List.of(MediaType.TEXT_PLAIN));
            return converter;
        }
    }

    @Configuration
    @ConditionalOnProperty(name = "route.policy", havingValue = "resource", matchIfMissing = true)
    public static class ResourceCountryLoaderConfiguration {
        @Bean(initMethod = "init")
        public ResourceCountryLoader remoteCountryLoader(ObjectMapper objectMapper,
                                                         @Value("${route.resource-policy.file}") String path) {
            return new ResourceCountryLoader(objectMapper, path);
        }
    }
}
