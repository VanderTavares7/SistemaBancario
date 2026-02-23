package com.vander.sistema_bancario.service;

import com.vander.sistema_bancario.domain.users.User;
import com.vander.sistema_bancario.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final UserRepository userRepository;

    // Constructor injection (recommended for immutability and testability)
    public CardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void requestCard() {

        // Retrieve the currently authenticated user's login from Spring Security context
        String login = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        // Fetch the user from the database using the login
        User user = (User) userRepository.findByLogin(login);

        // Business rule: users must be 18 or older to request a credit card
        if (user.getAge() < 18) {
            throw new RuntimeException("Users under the age of 18 cannot apply for a credit card.");
        }

        // If no exception is thrown, the card request is considered valid
        // (Further logic like saving the card request could be implemented here)
    }
}