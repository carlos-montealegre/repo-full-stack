package com.reto.industrial.microservicio.product.config;

import com.reto.industrial.microservicio.product.model.Product;
import com.reto.industrial.microservicio.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ProductSeeder {

    @Bean
    CommandLineRunner seedProducts(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                Product p1 = new Product("Laptop Lenovo", new BigDecimal("950.00"), 5, 1L);
                Product p2 = new Product("Monitor LG", new BigDecimal("220.00"), 10, 2L);
                Product p3 = new Product("Teclado Mecánico", new BigDecimal("89.99"), 15, 3L);

                productRepository.save(p1);
                productRepository.save(p2);
                productRepository.save(p3);

                System.out.println("🟢 Productos de prueba insertados.");
            } else {
                System.out.println("ℹ️ Productos ya existentes.");
            }
        };
    }
}