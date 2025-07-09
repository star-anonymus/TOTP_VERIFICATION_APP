package com.otpauthenticatioapp.OTP_Authentication_APP.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@Table(name = "users")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(name = "secretKey")
    private String secret;

    @Column(name = "otp_verifyed")
    private boolean OTPverified;

    @Column(name = "password")
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // No roles yet
    }

    @Override
    public String getPassword() {
        return password != null ? password : ""; // Avoid nulls
    }

    @Override
    public String getUsername() {
        return email;
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
}
