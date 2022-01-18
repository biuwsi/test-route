package com.modsen.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "countries-client", url = "${route.remote-policy.url}")
public interface CountriesClient {
    @GetMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    List<CountryResponse> getCountries();
}
