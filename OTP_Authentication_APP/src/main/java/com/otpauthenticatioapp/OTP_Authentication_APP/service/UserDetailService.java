package com.otpauthenticatioapp.OTP_Authentication_APP.service;

import com.otpauthenticatioapp.OTP_Authentication_APP.repository.RepositoryClass;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService
{
    private final RepositoryClass repositoryClass;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repositoryClass.findByemail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
