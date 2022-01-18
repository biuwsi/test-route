package com.modsen.controller;

import com.modsen.controller.api.RouteResponse;
import com.modsen.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.modsen.controller.ControllerConstants.ROUTE_FROM;
import static com.modsen.controller.ControllerConstants.ROUTE_TO;
import static com.modsen.controller.RouteController.ROOT;

@RestController
@RequestMapping(ROOT)
@RequiredArgsConstructor
public class RouteController {

    public static final String ROOT = "/routing";
    public static final String ROUTE_URI = "/{" + ROUTE_FROM + "}/{" + ROUTE_TO + "}";
    public static final String PATH_URI = ROOT + ROUTE_URI;


    private final RouteService routeService;

    @GetMapping(ROUTE_URI)
    public RouteResponse findRoute(@PathVariable(ROUTE_FROM) String from,
                                   @PathVariable(ROUTE_TO) String to) {
        return routeService.findRoute(from, to);
    }
}
