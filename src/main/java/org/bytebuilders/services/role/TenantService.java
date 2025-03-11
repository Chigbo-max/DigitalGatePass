package org.bytebuilders.services.role;

import org.bytebuilders.data.model.OtpLog;
import org.bytebuilders.data.repositories.OtpLogsRepository;
import org.bytebuilders.dtos.Requests.OtpRequest;
import org.bytebuilders.dtos.Responses.OtpResponse;
import org.bytebuilders.enums.Role;
import org.bytebuilders.utils.OtpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TenantService implements RoleService {

    @Autowired
    private OtpGenerator otpGenerator;

    @Autowired
    private OtpLogsRepository otpLogsRepository;

    @Override
    public Role getRole() {
        return Role.TENANT;
    }

    public OtpResponse generateOtp(OtpRequest otpRequest){
        return getOtpResponse(otpRequest);
    }

    public OtpResponse approveExit(OtpRequest otpRequest){
        return getOtpResponse(otpRequest);
    }

    private OtpResponse getOtpResponse(OtpRequest otpRequest) {
        String exitOtp = otpGenerator.generateOtp();
        LocalDateTime otpGeneratedTime = otpGenerator.createdTime();
        LocalDateTime otpExpiryTime = otpGenerator.expirationTime();

        String convertedOtpGeneratedTime = otpGeneratedTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy, hh:mm a"));
        String convertedOtpExpiryTime = otpExpiryTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy, hh:mm a"));

        OtpLog otpLog = new OtpLog( otpRequest.getVisitorName(), exitOtp, otpGeneratedTime, otpExpiryTime);
        otpLogsRepository.save(otpLog);

        OtpResponse otpResponse = new OtpResponse();
        otpResponse.setMessage("OTP successfully generated");
        otpResponse.setVisitorName(otpRequest.getVisitorName());
        otpResponse.setOtp(exitOtp);
        otpResponse.setCreatedTime(convertedOtpGeneratedTime);
        otpResponse.setExpiryTime(convertedOtpExpiryTime);
        return otpResponse;
    }
}
