package org.botnicholas.projects.authenticationservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${spring.security.cors.allowed.urls:*}")
    private List<String> corsAllowedUrls;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsConfigurer -> {
                    corsConfigurer.configurationSource(source -> {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(corsAllowedUrls);
                        config.setAllowedMethods(List.of("*"));
                        config.setAllowedHeaders(List.of("*"));

                        return config;
                    });
                })
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/auth/**"/*, "/home"*/).permitAll()
                        .requestMatchers("/**").authenticated())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
