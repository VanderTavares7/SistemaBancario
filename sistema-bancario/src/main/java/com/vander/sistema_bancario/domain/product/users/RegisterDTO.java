package com.vander.sistema_bancario.domain.product.users;

public record RegisterDTO(String login, String password, UserRole role) {
}