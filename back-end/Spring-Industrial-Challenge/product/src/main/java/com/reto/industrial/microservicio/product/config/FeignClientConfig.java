package com.reto.industrial.microservicio.product.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

// @Configuration
// public class FeignClientConfig {

//     @Bean
//     public RequestInterceptor authFeignInterceptor() {
//         return template -> {
//             String username = "admin";
//             String password = "admin123";
//             String credentials = username + ":" + password;
//             template.header("Authorization", "Basic " + credentials);
//         };
//     }
// }

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor authFeignInterceptor() {
        return template -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof JwtAuthenticationToken jwtAuth) {
                String tokenValue = jwtAuth.getToken().getTokenValue();
                template.header("Authorization", "Bearer " + tokenValue);
            }
        };
    }
}
