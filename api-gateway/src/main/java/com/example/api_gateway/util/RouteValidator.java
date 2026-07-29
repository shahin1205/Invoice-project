package com.example.api_gateway.util;


import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/login",
            "/auth/register"
    );

    public Predicate<ServerWebExchange> isSecured =
            exchange -> openApiEndpoints.stream()
                    .noneMatch(uri ->
                            exchange.getRequest()
                                    .getURI()
                                    .getPath()
                                    .contains(uri));
}
