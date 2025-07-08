package com.otpauthenticatioapp.OTP_Authentication_APP.service;

import com.otpauthenticatioapp.OTP_Authentication_APP.DTO.OTPVerificationdto;
import com.otpauthenticatioapp.OTP_Authentication_APP.entity.AppUser;
import com.otpauthenticatioapp.OTP_Authentication_APP.repository.RepositoryClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OtpLoginService
{
    private final RepositoryClass repositoryClass;

    /**
     * Log the user into Spring Security context after OTP verification.
     * @param email The email of the user to log in
     * @return true if authenticated successfully
     */
    public boolean loginUserAfterOtp(String email) {
        Optional<AppUser> userOpt = repositoryClass.findByemail(email);

        if (userOpt.isEmpty()) {
            return false;
        }

        AppUser user = userOpt.get();

        // Build a Spring Security compatible User object
        UserDetails springUser = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password("") // No password required after OTP
                .authorities("USER") // Assign any role you need
                .build();

        // Create an Authentication object
        Authentication auth = new UsernamePasswordAuthenticationToken(
                springUser,
                null,
                springUser.getAuthorities()
        );

        // Set the authentication in the Spring Security context
        SecurityContextHolder.getContext().setAuthentication(auth);

        return true;
    }
}
