package com.gabriel.configurations;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.filter.HttpServerFilter;
import jakarta.inject.Singleton;

public class WebConfiguration {

    @Singleton
    @Requires(beans = CorsFilter.class)
    HttpServerFilter corsFilter(com.gabriel.configurations.CorsFilter corsFilter) {
        return (request, chain) -> {
            if (isPreflightRequest(request)) {
                return chain.proceed(request);
            }
            return corsFilter.doFilter(request, chain);
        };
    }

    private boolean isPreflightRequest(HttpRequest<?> request) {
        return "OPTIONS".equalsIgnoreCase(request.getMethodName())
                && request.getHeaders().contains("Origin")
                && request.getHeaders().contains("Access-Control-Request-Method");
    }

}
