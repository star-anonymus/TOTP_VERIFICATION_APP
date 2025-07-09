package com.otpauthenticatioapp.OTP_Authentication_APP.service;

import com.otpauthenticatioapp.OTP_Authentication_APP.entity.SMSUser;
import com.otpauthenticatioapp.OTP_Authentication_APP.repository.SMSRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class SMSOTPService
{
    private final SMSRepository smsRepository;
    public SMSOTPService(SMSRepository smsRepository1)
    {
        this.smsRepository=smsRepository1;
    }
    @Value("${twilio.phone.number}")
    private String fromphonenumber;

    public void sendOtp(String phonenumber, String otp) {
        try {
            Message.creator(
                    new PhoneNumber(phonenumber),
                    new PhoneNumber(fromphonenumber),
                    "Your OTP is: " + otp
            ).create();
        } catch (Exception e) {
            System.out.println("❌ Twilio failed: " + e.getMessage());
        }
    }

    public void SendOTPAndSave(String phonenumber)
    {
        System.out.println(" Sending OTP to: " + phonenumber);  // Add this line
        String otp = generateOtp();

        Message.creator(
                new PhoneNumber(phonenumber),
                new PhoneNumber("+14173918250"), // Or use `fromphonenumber`
                "Your OTP is: " + otp
        ).create();

        SMSUser user = new SMSUser();
        user.setPhonenumber(phonenumber);
        user.setOtp(otp);
        user.setCreatedAT(LocalDateTime.now());

        smsRepository.save(user);
    }

    public boolean verifyOTP(String phonenumber,String InputOTP){
        Optional<SMSUser> user= smsRepository.findByPhonenumber(phonenumber);
        if (user.isPresent())
        {
            SMSUser User=user.get();
            if(User.getOtp().equals(InputOTP))
            {
                User.setOtpVerifiyed(true);
                smsRepository.save(User);
                return true;
            }
        }
        return false;
    }
    public String generateOtp()
    {
        int otp=100000 + new java.util.Random().nextInt(900000);
        return String.valueOf(otp);
    }
}
