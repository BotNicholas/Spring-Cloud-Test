package org.botnicholas.projects.apigatewaymicro;

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
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("math", r -> r.path("/math/request-questions")
                        .filters(f -> f.rewritePath("/math/request-questions", "/api/questions"))
                        .uri("lb://MATHMICRO")) /*lb: here shands for Load Balancing (The same as is Examinator microservice - @LoadBalanced upon RestTemplate)*/
                .route("history", r -> r.path("/history/request-questions")
                        .filters(f -> f.rewritePath("/history/request-questions", "/api/questions"))
                        .uri("lb://HISTORYMICRO"))
                .route("examenator", r -> r.path("/examenator/request-questions")
                        .filters(f -> f.rewritePath("/examenator/request-questions", "/exam"))
                        .uri("lb://EXAMINATORMICRO"))
                .route("Subjects", r -> r.path("/subjects/get-available-sync")
                    .filters(f -> f.rewritePath("/subjects/get-available-sync", "/api/get-subgects-sync"))
                    .uri("lb://EXAMENSSUBJECTSINFORMERMICRO"))
                .build();
    }
}
