package org.botnicholas.projects.apigatewaymicro.filters;

import org.botnicholas.projects.apigatewaymicro.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class AuthGatewayFilter implements GatewayFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

//        Verifying if no Authorization header was passed
        if (!exchange.getRequest().getHeaders().containsKey("Authorization")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = extractToken(exchange);

//        Verifying if token was set correctly
        if (token == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

//        Verifying if token is valid.
//        In scope of optimisation and security token validation is implemented also here...
//        Anyway nobody stops you to make a REST call ;)
        if (jwtUtils.validateAccessToken(token)) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    private String extractToken(ServerWebExchange exchange) {
        String header = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }

        return header.substring(7);
    }
}
