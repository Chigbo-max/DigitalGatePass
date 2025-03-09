package org.bytebuilders.dtos.Requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ValidateOtpRequest {
    @NotBlank(message="OTP is required")
    private String otp;

}
