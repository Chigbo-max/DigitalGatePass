package org.bytebuilders.dtos.Responses;

import lombok.Getter;
import lombok.Setter;
import org.bytebuilders.enums.OtpValidationStatus;

@Getter
@Setter
public class ValidateOtpResponse {
    private String message;
    private OtpValidationStatus status;
}
