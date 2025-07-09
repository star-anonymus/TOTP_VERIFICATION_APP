package com.otpauthenticatioapp.OTP_Authentication_APP.controller;

import com.otpauthenticatioapp.OTP_Authentication_APP.DTO.SMSDTOREQUEST;
import com.otpauthenticatioapp.OTP_Authentication_APP.entity.SMSUser;
import com.otpauthenticatioapp.OTP_Authentication_APP.repository.SMSRepository;
import com.otpauthenticatioapp.OTP_Authentication_APP.service.SMSOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/sms")
public class SMSController
{
    private final SMSOTPService smsotpService;
    private final SMSRepository smsRepository;

    @Autowired
    public SMSController(SMSOTPService smsotpService, SMSRepository smsRepository) {
        this.smsotpService = smsotpService;
        this.smsRepository = smsRepository;
    }

    @PostMapping("/send_SMS")
    public ResponseEntity<String> sendOTP(@RequestBody SMSDTOREQUEST dto)
    {
        smsotpService.SendOTPAndSave(dto.getPhonenumber());
        return ResponseEntity.ok("OTP send to :"+dto.getPhonenumber());
    }

    @PostMapping("/verify_SMS")
    public ResponseEntity<String> verifyOTP(@RequestBody SMSDTOREQUEST dto)
    {
        Optional<SMSUser>  user= smsRepository.findByPhonenumber(dto.getPhonenumber());
        if (user.isPresent() && user.get().getOtp().equals(dto.getOtp())) {
            return ResponseEntity.ok(" OTP Verified Successfully");
        } else {
            return ResponseEntity.badRequest().body(" Invalid OTP or phone number");
        }
    }
}
