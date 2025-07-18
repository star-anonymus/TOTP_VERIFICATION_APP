package com.otpauthenticatioapp.OTP_Authentication_APP.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfiguration {

    private String accountSid;
    private String authToken;
    private String phoneNumber;

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
        System.out.println(" Twilio Initialized");
    }
}