package org.bytebuilders.services.role;


import org.bytebuilders.data.model.OtpLog;
import org.bytebuilders.data.repositories.OtpLogsRepository;
import org.bytebuilders.dtos.Requests.ValidateOtpRequest;
import org.bytebuilders.dtos.Responses.ValidateOtpResponse;
import org.bytebuilders.enums.OtpValidationStatus;
import org.bytebuilders.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SecurityService implements RoleService {

    @Autowired
    private OtpLogsRepository otpLogsRepository;

    @Override
    public Role getRole() {
        return Role.SECURITY;
    }

    public ValidateOtpResponse validateOtp(ValidateOtpRequest request) {
        ValidateOtpResponse response = new ValidateOtpResponse();
       Optional<OtpLog> otpLog = otpLogsRepository.findByOtp(request.getOtp());

        if (otpLog.isEmpty()) {
            response.setMessage("OTP not found or invalid");
            response.setStatus(OtpValidationStatus.INVALID);
            return response;
        }

        if (LocalDateTime.now().isAfter(otpLog.get().getExpirationTime())) {
            response.setMessage("OTP is expired");
            response.setStatus(OtpValidationStatus.EXPIRED);
        }
        else {
            response.setMessage("OTP is valid");
            response.setStatus(OtpValidationStatus.VALID);
        }

        return response;
    }
}
