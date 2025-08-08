package com.reto.industrial.microservicio.product.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;
    private Integer stock;
    private Long createdByUserId;

    // Getters, setters, constructor
     public Product(String name, BigDecimal price, int stock, long createdByUserId) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.createdByUserId = createdByUserId;

    }
    
}

