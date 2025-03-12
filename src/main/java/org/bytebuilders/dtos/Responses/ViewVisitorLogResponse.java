package org.bytebuilders.dtos.Responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ViewVisitorLogResponse {
    private String otpId;
    private String visitorName;
    private String otp;
    private String createdTime;
    private String expiryTime;
}
