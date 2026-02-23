package com.vander.sistema_bancario.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.vander.sistema_bancario.domain.users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Secret key used to sign and validate JWT tokens
    // The value is injected from application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            // Define the signing algorithm using HMAC256 and the secret key
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    // Identifies who issued the token
                    .withIssuer("auth-api")

                    // Defines the subject of the token (usually the username or user ID)
                    .withSubject(user.getLogin())

                    // Sets the token expiration date
                    .withExpiresAt(genExpirationDate())

                    // Signs the token with the defined algorithm
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            // Thrown if there is an error during token creation
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token){
        try {
            // Define the same signing algorithm used during token generation
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    // Ensures the token was issued by the expected issuer
                    .withIssuer("auth-api")

                    // Builds the verification instance
                    .build()

                    // Verifies token signature and expiration
                    .verify(token)

                    // Returns the subject (username) if the token is valid
                    .getSubject();

        } catch (JWTVerificationException exception){
            // If verification fails (expired, invalid signature, malformed token)
            return null;
        }
    }

    // Generates token expiration time (2 hours from now)
    private Instant genExpirationDate(){
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}