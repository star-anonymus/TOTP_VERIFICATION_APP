package com.otpauthenticatioapp.OTP_Authentication_APP.controller;

import com.otpauthenticatioapp.OTP_Authentication_APP.DTO.OTPResponseDTO;
import com.otpauthenticatioapp.OTP_Authentication_APP.DTO.OTPVerificationdto;
import com.otpauthenticatioapp.OTP_Authentication_APP.DTO.UserRegisterationDTO;
import com.otpauthenticatioapp.OTP_Authentication_APP.service.OtpLoginService;
import com.otpauthenticatioapp.OTP_Authentication_APP.service.otpservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/otp")
@RequiredArgsConstructor
public class ApplicationController
{
    private final otpservice otpService;
    private final OtpLoginService otpLoginService;

    /**
     * Register a new user and generate QR Code URL for Google Authenticator
     */
    @PostMapping("/register")
    public ResponseEntity<OTPResponseDTO> registerUser(@RequestBody UserRegisterationDTO dto) {
        OTPResponseDTO response = otpService.registerUser(dto);
        return ResponseEntity.ok(response);
    }

    /**
     * Verify the OTP and log the user in
     */
    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody OTPVerificationdto dto) {
        boolean valid = otpService.verifyOtp(dto);

        if (valid) {
            otpLoginService.loginUserAfterOtp(dto.getEmail()); // Log user into Spring Security context
            return ResponseEntity.ok("OTP verified & user logged in.");
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP.");
        }
    }

}
