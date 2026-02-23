package com.vander.sistema_bancario.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    // Custom JWT filter that validates tokens on every request
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity

                // Disable CSRF since we are using stateless JWT authentication
                .csrf(csrf -> csrf.disable())

                // Configure session management to be stateless (no HTTP session)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configure authorization rules
                .authorizeHttpRequests(authorize -> authorize

                        // Public endpoints (no authentication required)
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        // Protected endpoints (require USER or ADMIN role)
                        .requestMatchers(HttpMethod.POST, "/card/***").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/product/***").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/user/***").hasAnyRole("USER", "ADMIN")

                        // All other requests must be authenticated
                        .anyRequest().authenticated()
                )

                // Add custom JWT filter before Spring's default authentication filter
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {

        // Exposes AuthenticationManager bean for use in authentication endpoints
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        // BCrypt is recommended for secure password hashing
        return new BCryptPasswordEncoder();
    }
}