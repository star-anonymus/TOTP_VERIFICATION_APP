package com.otpauthenticatioapp.OTP_Authentication_APP.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTPResponseDTO
{
    public String qrcodeURL;
    public String secret;
}
