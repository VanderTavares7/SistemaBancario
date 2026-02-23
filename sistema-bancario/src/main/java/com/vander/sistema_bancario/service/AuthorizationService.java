package com.vander.sistema_bancario.service;

import com.vander.sistema_bancario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    // Injecting the repository to access user data from the database
    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // This method is used by Spring Security during authentication
        // It loads the user based on the provided username (login)

        return repository.findByLogin(username);

        // IMPORTANT:
        // If the user is not found, this method should throw UsernameNotFoundException.
        // Returning null may cause unexpected authentication errors.
    }
}