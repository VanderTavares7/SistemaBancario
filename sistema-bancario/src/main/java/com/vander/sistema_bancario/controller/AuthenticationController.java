package com.vander.sistema_bancario.controller;

import com.vander.sistema_bancario.domain.users.AuthenticationDTO;
import com.vander.sistema_bancario.domain.users.LoginResponseDTO;
import com.vander.sistema_bancario.domain.users.RegisterDTO;
import com.vander.sistema_bancario.domain.users.User;
import com.vander.sistema_bancario.infra.security.TokenService;
import com.vander.sistema_bancario.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){

        if (repository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().body("Login already exists.");
        }

        if (repository.existsByEmail(data.email())) {
            return ResponseEntity.badRequest().body("Email already registered.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(
                data.login(),
                encryptedPassword,
                data.role(),
                data.birthDate(),
                data.balance(),
                data.creditLimit(),
                data.email()
        );
        repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}