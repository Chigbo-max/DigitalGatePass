package org.bytebuilders.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class OtpGenerator {

    @Value("${otp.expiration.minutes}")
    private int expirationMinutes;

    public String generateOtp() {
        return getRandom();
    }

    public String getRandom() {
        Random rand = new Random();
        long randomResult = rand.nextInt(999);
        String otp = "VE" + String.valueOf(randomResult);
        return otp;
    }

    public LocalDateTime createdTime(){
        return LocalDateTime.now();
    }

    public LocalDateTime expirationTime() {
        return LocalDateTime.now().plusMinutes(expirationMinutes);
    }
}
