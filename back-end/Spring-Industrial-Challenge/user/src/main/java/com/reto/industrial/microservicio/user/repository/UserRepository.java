package com.reto.industrial.microservicio.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reto.industrial.microservicio.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

