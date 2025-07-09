package com.otpauthenticatioapp.OTP_Authentication_APP.DTO;

import lombok.Data;

@Data
public class SMSDTOREQUEST
{
    private String phonenumber;
    private String otp;
}
