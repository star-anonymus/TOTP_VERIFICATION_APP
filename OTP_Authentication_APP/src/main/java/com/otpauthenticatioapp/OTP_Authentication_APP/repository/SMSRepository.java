package com.otpauthenticatioapp.OTP_Authentication_APP.repository;

import com.otpauthenticatioapp.OTP_Authentication_APP.entity.SMSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SMSRepository extends JpaRepository<SMSUser, String> {
    Optional<SMSUser> findByPhonenumber(String phoneumber);
}
