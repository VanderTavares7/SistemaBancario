package com.vander.sistema_bancario.infra.security;

import com.vander.sistema_bancario.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    // Service responsible for validating and generating JWT tokens
    private final TokenService tokenService;

    // Repository used to load user details from the database
    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Extract token from Authorization header
        var token = recoverToken(request);

        if (token != null) {

            // Validate the token and retrieve the username (subject)
            var login = tokenService.validateToken(token);

            // Load user details from database
            UserDetails user = userRepository.findByLogin(login);

            if (user != null) {

                // Create authentication object with user authorities (roles/permissions)
                var authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );

                // Set authenticated user into SecurityContext
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        // Continue filter chain
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {

        // Retrieve Authorization header
        var authHeader = request.getHeader("Authorization");

        // Validate header format
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        // Remove "Bearer " prefix and return the token
        return authHeader.replace("Bearer ", "");
    }
}