package com.otpauthenticatioapp.OTP_Authentication_APP.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OTPResponseDTO
{
    public String qrcodeURL;
    public String secret;
}
