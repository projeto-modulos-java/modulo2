package com.modulo2.auth.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import com.modulo2.auth.configurations.security.JWTFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private JWTFilter jwtFilter;

    public SecurityConfiguration(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    SecurityFilterChain filterchain(HttpSecurity http){
        return http
        .authorizeHttpRequests(auth -> 
            auth
            .requestMatchers("/me")
            .authenticated()
            .anyRequest()
            .permitAll()
        )
        .addFilterBefore(jwtFilter, AnonymousAuthenticationFilter.class)
        .csrf(csrf -> csrf.disable())
        .build();
    }

    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
