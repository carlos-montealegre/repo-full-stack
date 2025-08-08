package com.reto.industrial.microservicio.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reto.industrial.microservicio.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

