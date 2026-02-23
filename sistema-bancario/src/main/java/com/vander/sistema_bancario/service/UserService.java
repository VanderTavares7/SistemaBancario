package com.vander.sistema_bancario.service;

import com.vander.sistema_bancario.domain.users.User;
import com.vander.sistema_bancario.dto.UserDTO;
import com.vander.sistema_bancario.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor injection (preferred approach for better testability and immutability)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO userFindById(Long id) {

        // Retrieve the user or throw a custom exception if not found
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a DTO to expose only necessary data to the client
        UserDTO userDTO = new UserDTO();

        // Manually map entity fields to the DTO
        // This prevents exposing sensitive internal data (like password)
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setLogin(user.getLogin());
        userDTO.setBalance(user.getBalance());
        userDTO.setCreditLimit(user.getCreditLimit());
        userDTO.setBirthDate(user.getBirthDate());

        // Return the mapped DTO instead of the entity
        return userDTO;
    }
}