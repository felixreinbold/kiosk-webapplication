package com.kiosk.kioskbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // für POST-Tests wichtig!
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // ALLES erlauben (auch POST, DELETE, etc.)
                )
                .httpBasic(Customizer.withDefaults()); // optional, aber hilfreich für spätere Tests

        return http.build();
    }
}
