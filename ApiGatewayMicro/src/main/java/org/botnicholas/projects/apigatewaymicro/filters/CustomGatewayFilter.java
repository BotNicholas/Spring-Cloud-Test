package org.botnicholas.projects.apigatewaymicro.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomGatewayFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Custom Gateway pre Filter for: " + exchange.getRequest().getURI());
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    System.out.println("Custom Gateway post Filter for: " + exchange.getRequest().getURI());
                }));
    }
}
