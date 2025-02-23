package org.botnicholas.projects.apigatewaymicro;

import org.botnicholas.projects.apigatewaymicro.filters.AuthGatewayFilter;
import org.botnicholas.projects.apigatewaymicro.filters.CustomGatewayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayMicroApplication.class, args);
    }

    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, CustomGatewayFilter customFilter) {
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AuthGatewayFilter authFilter) {
        return builder.routes()
                .route("authenticationHome", r -> r.path("/authentication/home")
                        .filters(f -> f.rewritePath("/authentication/home", "/home"))
                        .uri("lb://AUTHENTICATIONSERVICE"))
                .route("authentication", r -> r.path("/authentication/**")
                        .filters(f -> f.rewritePath("/authentication/", "/auth/"))
                        .uri("lb://AUTHENTICATIONSERVICE"))
                .route("math", r -> r.path("/math/request-questions")
//                        .filters(f -> f.filter(customFilter).rewritePath("/math/request-questions", "/api/questions"))
                        .filters(f -> f.filter(authFilter).filter(new CustomGatewayFilter()).rewritePath("/math/request-questions", "/api/questions"))
                        .uri("lb://MATHMICRO")) /*lb: here shands for Load Balancing (The same as is Examinator microservice - @LoadBalanced upon RestTemplate)*/
                .route("history", r -> r.path("/history/request-questions")
                        .filters(f -> f.filter(authFilter).rewritePath("/history/request-questions", "/api/questions"))
                        .uri("lb://HISTORYMICRO"))
                .route("examenator", r -> r.path("/examenator/request-questions")
                        .filters(f -> f.filter(authFilter).rewritePath("/examenator/request-questions", "/exam"))
                        .uri("lb://EXAMINATORMICRO"))
                .route("Subjects", r -> r.path("/subjects/get-available-sync")
                    .filters(f -> f.rewritePath("/subjects/get-available-sync", "/api/get-subgects-sync"))
                    .uri("lb://EXAMENSSUBJECTSINFORMERMICRO"))
                .build();
    }
}
