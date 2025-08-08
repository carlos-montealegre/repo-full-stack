package com.reto.industrial.microservicio.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.reto.industrial.microservicio.product.config.FeignClientConfig;
import com.reto.industrial.microservicio.product.model.UserDto;

@FeignClient(name = "users-service", configuration = FeignClientConfig.class)
public interface UserClient {
    @GetMapping("/api/users/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
}

