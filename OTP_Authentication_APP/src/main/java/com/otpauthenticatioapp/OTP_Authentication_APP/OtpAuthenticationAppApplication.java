package com.otpauthenticatioapp.OTP_Authentication_APP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.otpauthenticatioapp.OTP_Authentication_APP.repository")
@EntityScan(basePackages = "com.otpauthenticatioapp.OTP_Authentication_APP.entity")
@ComponentScan(basePackages = "com.otpauthenticatioapp.OTP_Authentication_APP")
public class OtpAuthenticationAppApplication
{
	public static void main(String[] args) {
		SpringApplication.run(OtpAuthenticationAppApplication.class, args);
	}
}
