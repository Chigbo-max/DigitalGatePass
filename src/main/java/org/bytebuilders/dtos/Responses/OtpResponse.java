package org.bytebuilders.dtos.Responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpResponse {
    private String message;
    private String otpId;
    private String visitorName;
    private String otp;
    private String address;
    private String createdTime;
    private String expiryTime;
}
