package com.reto.industrial.microservicio.user.config;

import com.reto.industrial.microservicio.user.model.User;
import com.reto.industrial.microservicio.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserSeeder {

    @Bean
    CommandLineRunner seedUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                User u1 = new User("admin", "admin@mail.com", "admin123", "ADMIN");
                User u2 = new User("juan", "juan@mail.com", "123456", "USER");
                User u3 = new User("ana", "ana@mail.com", "pass123", "USER");

                userRepository.save(u1);
                userRepository.save(u2);
                userRepository.save(u3);

                System.out.println("🟢 Usuarios de prueba insertados correctamente.");
            } else {
                System.out.println("ℹ️ Usuarios ya existentes, se omite seed.");
            }
        };
    }
}
