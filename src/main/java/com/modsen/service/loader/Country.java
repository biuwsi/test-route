package com.modsen.service.loader;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Country {
    @NonNull
    String name;
    @NonNull
    List<String> borders;
}
