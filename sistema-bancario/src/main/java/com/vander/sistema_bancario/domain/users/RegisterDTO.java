package com.vander.sistema_bancario.domain.users;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegisterDTO(String login, String password, UserRole role, LocalDate birthDate, BigDecimal balance, BigDecimal creditLimit, String email) {
}