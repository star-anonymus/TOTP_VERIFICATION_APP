package com.otpauthenticatioapp.OTP_Authentication_APP.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "SMS_User_Details")
public class SMSUser {
    @Id
    @Column(name = "PhoneNumber", nullable = false, unique = true)
    private String phonenumber;

    private String otp;

    private LocalDateTime createdAT;

    private boolean otpVerifiyed;
}
