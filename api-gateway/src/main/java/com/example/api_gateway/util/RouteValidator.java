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

    private static final List<Permission> PERMISSIONS = List.of(

            // Product Service
            new Permission("POST", "/product/**", List.of("ADMIN")),
            new Permission("PUT", "/product/**", List.of("ADMIN")),
            new Permission("DELETE", "/product/**", List.of("ADMIN")),
            new Permission("GET", "/product/**", List.of("ALL")),

            // Customer Service
            new Permission("POST", "/customer/**", List.of("ADMIN")),
            new Permission("PUT", "/customer/**", List.of("ADMIN", "MANAGER")),
            new Permission("DELETE", "/customer/**", List.of("ADMIN")),
            new Permission("GET", "/customer/**", List.of("ADMIN", "USER", "MANAGER")),

            // Invoice Service
            new Permission("POST", "/invoice/**", List.of("ADMIN")),
            new Permission("PUT", "/invoice/**", List.of("ADMIN")),
            new Permission("DELETE", "/invoice/**", List.of("ADMIN")),
            new Permission("GET", "/invoice/**", List.of("ADMIN", "USER", "MANAGER")),

            // Payment Service
            new Permission("POST", "/payment/**", List.of("ADMIN")),
            new Permission("PUT", "/payment/**", List.of("ADMIN")),
            new Permission("DELETE", "/payment/**", List.of("ADMIN")),
            new Permission("GET", "/payment/**", List.of("ADMIN"))
    );
//    public boolean hasAccess(String path, String role) {
//
//        List<String> allowedRoles = ROLE_ACCESS.get(path);
//
//        if (allowedRoles == null) {
//            return true;
//        }
//
//        return allowedRoles.contains(role);
//    }

    public boolean hasAccess(String method, String path, String role) {

        for (Permission permission : PERMISSIONS) {

            boolean methodMatch =
                    permission.getMethod().equalsIgnoreCase(method);

            String prefix =
                    permission.getPathPattern().replace("/**", "");

            boolean pathMatch =
                    path.equals(prefix) ||
                            path.startsWith(prefix + "/");

            if (methodMatch && pathMatch) {

                if (permission.getRoles().contains("ALL")) {
                    return true;
                }

                return permission.getRoles().contains(role);
            }
        }

        return false;
    }
}
