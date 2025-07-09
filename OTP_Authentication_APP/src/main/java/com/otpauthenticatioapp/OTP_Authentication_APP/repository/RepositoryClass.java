package com.otpauthenticatioapp.OTP_Authentication_APP.repository;

import com.otpauthenticatioapp.OTP_Authentication_APP.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface RepositoryClass extends JpaRepository<AppUser,Long>
{
    Optional<AppUser> findByemail(String email);
}
