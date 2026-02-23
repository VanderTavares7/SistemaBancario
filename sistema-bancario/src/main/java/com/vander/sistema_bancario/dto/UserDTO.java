package com.vander.sistema_bancario.dto;

import com.vander.sistema_bancario.domain.users.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String login;
    private UserRole role;
    private LocalDate birthDate;
    private BigDecimal balance;
    private BigDecimal creditLimit;
}
