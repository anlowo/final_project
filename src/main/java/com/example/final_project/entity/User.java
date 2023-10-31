package com.example.final_project.entity;

import com.example.final_project.entity.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Login cannot be empty")
    @Column(name = "login")
    private String login;

    @NotBlank(message = "Password cannot be empty")
    @Column(name = "password")
    private String password;

    @Transient
    @NotBlank(message = "Password confirmation cannot be empty")
    private String password2;

    @Column(name = "email")
    private String email;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "roles")
    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;

    public boolean isAdmin() {
        return roles.contains(Roles.ADMIN);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
