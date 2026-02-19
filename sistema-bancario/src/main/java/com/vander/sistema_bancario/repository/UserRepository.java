package com.vander.sistema_bancario.repository;

import com.vander.sistema_bancario.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);

    boolean existsByEmail(String email);
}