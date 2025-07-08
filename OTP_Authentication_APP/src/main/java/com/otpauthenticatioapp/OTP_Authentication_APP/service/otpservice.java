package com.otpauthenticatioapp.OTP_Authentication_APP.service;

import com.otpauthenticatioapp.OTP_Authentication_APP.DTO.OTPResponseDTO;
import com.otpauthenticatioapp.OTP_Authentication_APP.DTO.OTPVerificationdto;
import com.otpauthenticatioapp.OTP_Authentication_APP.DTO.UserRegisterationDTO;
import com.otpauthenticatioapp.OTP_Authentication_APP.entity.AppUser;
import com.otpauthenticatioapp.OTP_Authentication_APP.repository.RepositoryClass;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class otpservice
{
    @Autowired
    public final RepositoryClass repositoryClass;

    public OTPResponseDTO registerUser(UserRegisterationDTO user)
    {
        GoogleAuthenticator gAuth=new GoogleAuthenticator();
        GoogleAuthenticatorKey gAuthkey= gAuth.createCredentials();

        String secret= gAuthkey.getKey();
        String urlOTP= String.format("otpauth://totp/OTP_Verification_APP:user@example.com?secret=A2R7KIWAC7U4K6EW3VRK6ZI4MDBJJWZI&issuer=OTP_Verification_APP\n",
                URLEncoder.encode(user.getEmail()), StandardCharsets.UTF_8
        ,URLEncoder.encode(secret,StandardCharsets.UTF_8));

        AppUser us=  new AppUser();
        us.setEmail(user.getEmail());
        us.setSecret(secret);
        us.setOTPverified(false);
        repositoryClass.save(us);

        return new OTPResponseDTO(urlOTP,secret);

    }

    public boolean verifyOtp(OTPVerificationdto dto) {
        Optional<AppUser> userOpt = repositoryClass.findByemail(dto.getEmail());

        if (userOpt.isEmpty()) {
            return false;
        }

        AppUser user = userOpt.get();

        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        boolean isValid = gAuth.authorize(user.getSecret(), Integer.parseInt(dto.getOtp()));

        if (isValid) {
            user.setOTPverified(true);
            repositoryClass.save(user);
        }

        return isValid;
    }

}
