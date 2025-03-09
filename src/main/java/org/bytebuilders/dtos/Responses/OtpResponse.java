package org.bytebuilders.dtos.Responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpResponse {
    public String message;
    public String visitorName;
    public String otp;
    public String createdTime;
    public String expiryTime;
}
