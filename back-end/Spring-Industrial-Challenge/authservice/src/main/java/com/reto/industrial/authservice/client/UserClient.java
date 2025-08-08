package com.reto.industrial.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.reto.industrial.authservice.model.UserDto;

@FeignClient(name = "users-service")
public interface UserClient {
    @GetMapping("/api/users/username/{username}")
    UserDto getByUsername(@PathVariable("username") String username);
}

