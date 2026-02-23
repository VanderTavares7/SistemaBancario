package com.vander.sistema_bancario.domain.users;

import com.vander.sistema_bancario.entity.PurchaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private UserRole role;
    private LocalDate birthDate;
    private BigDecimal balance;
    private BigDecimal creditLimit;
    private String email;

    public User(String login, String password, UserRole role, LocalDate birthDate, BigDecimal balance, BigDecimal creditLimit, String email) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.birthDate = birthDate;
        this.balance = balance;
        this.creditLimit = creditLimit;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @OneToMany(mappedBy = "user")
    private List<PurchaseEntity> purchases;

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public int getAge() {
        return java.time.Period.between(this.birthDate, java.time.LocalDate.now()).getYears();
    }
}
