package com.vander.sistema_bancario.service;

import com.vander.sistema_bancario.domain.users.User;
import com.vander.sistema_bancario.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final UserRepository userRepository;

    public CardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void requestCard() {

        String login = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = (User) userRepository.findByLogin(login);

        if (user.getAge() < 18) {
            throw new RuntimeException("Users under the age of 18 cannot apply for a credit card.");
        }

    }
}
