package com.example.api_gateway.util;


import java.util.List;

public class Permission {

    private final String method;
    private final String pathPattern;
    private final List<String> roles;

    public Permission(String method, String pathPattern, List<String> roles) {
        this.method = method;
        this.pathPattern = pathPattern;
        this.roles = roles;
    }

    public String getMethod() {
        return method;
    }

    public String getPathPattern() {
        return pathPattern;
    }

    public List<String> getRoles() {
        return roles;
    }
}
