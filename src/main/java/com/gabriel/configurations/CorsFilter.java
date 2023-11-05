package com.gabriel.configurations;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.*;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

import java.util.List;

@Singleton
@Requires(property = "cors.enabled", value = "true")
public class CorsFilter implements HttpServerFilter {

    private final List<String> allowedOrigins = List.of("http://localhost:8080");

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE; // Make sure this filter is applied first
    }

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        if (isPreflightRequest(request)) {
            MutableHttpResponse<?> response = HttpResponse.ok()
                    .header("Access-Control-Allow-Origin", request.getHeaders().get("Origin", String.class).orElse("*"))
                    .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Max-Age", "1800");
        } else {
            return chain.proceed(request);
        }

        return null;
    }

    private boolean isPreflightRequest(HttpRequest<?> request) {
        HttpHeaders headers = request.getHeaders();
        return HttpMethod.OPTIONS.equals(request.getMethod()) &&
                headers.contains("Origin") &&
                headers.contains("Access-Control-Request-Method");
    }
}
