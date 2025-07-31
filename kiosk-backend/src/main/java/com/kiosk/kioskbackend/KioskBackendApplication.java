package com.kiosk.kioskbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.kiosk.kioskbackend.model.Role;
import com.kiosk.kioskbackend.model.User;
import com.kiosk.kioskbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class KioskBackendApplication {


    @Bean
    public CommandLineRunner initAdmin(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = User.builder()
                        .username("admin")
                        .password(new BCryptPasswordEncoder().encode("admin123"))
                        .email("admin@kiosk.de")
                        .role(Role.ADMIN)
                        .build();
                userRepository.save(admin);
                System.out.println("Admin erstellt: admin / admin123");
            }
        };
    }

    @Bean
    public CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .email("felix.reinbold2003@gmail.com")
                        .role(Role.ADMIN)
                        .build();
                userRepository.save(admin);
                System.out.println("✅ Admin-User angelegt mit Passwort: admin123");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(KioskBackendApplication.class, args);
    }

}
