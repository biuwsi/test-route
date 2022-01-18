package com.modsen.controller.api;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class RouteResponse {
    List<String> route;
}
