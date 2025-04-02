package com.youicha.gateway_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

/**
 * Configuration class for setting up security and CORS for the gateway service
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures the security filter chain for the application
     * This method sets up CORS support, disables CSRF protection, and configures authorization
     * rules for HTTP exchanges. It allows unrestricted access to actuator endpoints and requires
     * authentication for all other endpoints, using HTTP Basic authentication
     *
     * @param http the ServerHttpSecurity instance to configure
     * @return the configured SecurityWebFilterChain
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                // Enable CORS and disable CSRF protection
                .cors().and().csrf().disable()
                // Configure authorization rules
                .authorizeExchange(exchange -> exchange
                        // Permit all requests to actuator endpoints
                        .pathMatchers("/actuator/**").permitAll()
                        // Require authentication for any other request
                        .anyExchange().authenticated()
                )
                // Enable HTTP Basic authentication
                .httpBasic();
        return http.build();
    }

    /**
     * Configures the CORS (Cross-Origin Resource Sharing) settings for the application
     * This method creates a CorsConfigurationSource bean that allows requests from any origin
     * and permits GET, POST, PUT, and DELETE HTTP methods with any headers
     *
     * @return the CorsConfigurationSource containing the CORS configuration
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Allow all origins
        configuration.setAllowedOrigins(Arrays.asList("*"));
        // Allow specified HTTP methods
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        // Allow all headers
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // Register the configuration for all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}