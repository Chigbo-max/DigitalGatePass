package org.bytebuilders.services.role;

import org.bytebuilders.data.model.OtpLog;
import org.bytebuilders.data.model.User;
import org.bytebuilders.data.model.VisitorLog;
import org.bytebuilders.data.repositories.OtpLogsRepository;
import org.bytebuilders.data.repositories.UsersRepository;
import org.bytebuilders.data.repositories.VisitorLogRepository;
import org.bytebuilders.dtos.Requests.OtpRequest;
import org.bytebuilders.dtos.Responses.OtpResponse;
import org.bytebuilders.enums.Role;
import org.bytebuilders.exceptions.IllegalAuthException;
import org.bytebuilders.utils.OtpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TenantService implements RoleService {

    @Autowired
    private OtpGenerator otpGenerator;

    @Autowired
    private OtpLogsRepository otpLogsRepository;

    @Autowired
    private VisitorLogRepository visitorLogsRepository;
    @Autowired
    private UsersRepository usersRepository;


    @Override
    public Role getRole() {
        return Role.TENANT;
    }

    public OtpResponse generateOtp(OtpRequest otpRequest) {
        return getOtpResponse(otpRequest);
    }

    public OtpResponse approveExit(OtpRequest otpRequest) {
        return getOtpResponse(otpRequest);
    }

    private OtpResponse getOtpResponse(OtpRequest otpRequest) {
        String otp = otpGenerator.generateOtp();
        LocalDateTime otpGeneratedTime = otpGenerator.createdTime();
        LocalDateTime otpExpiryTime = otpGenerator.expirationTime();

        String convertedOtpGeneratedTime = otpGeneratedTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy, hh:mm a"));
        String convertedOtpExpiryTime = otpExpiryTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy, hh:mm a"));

        String address = getTenantAddress(otpRequest.getTenantEmail());
        System.out.println(address);

        OtpLog otpLog = new OtpLog( otpRequest.getVisitorName(), otp, address,otpGeneratedTime, otpExpiryTime);
        otpLogsRepository.save(otpLog);


        VisitorLog visitorLog = new VisitorLog( otpRequest.getVisitorName(), otp,address ,otpGeneratedTime, otpExpiryTime);
        visitorLogsRepository.save(visitorLog);

        OtpResponse otpResponse = new OtpResponse();
        otpResponse.setMessage("OTP successfully generated");
        otpResponse.setOtpId(otpLog.getId());
        otpResponse.setVisitorName(otpRequest.getVisitorName());
        otpResponse.setOtp(otp);
        otpResponse.setAddress(address);
        otpResponse.setCreatedTime(convertedOtpGeneratedTime);
        otpResponse.setExpiryTime(convertedOtpExpiryTime);
        return otpResponse;
    }

    private String getTenantAddress(String tenantEmail) {
        User tenant = usersRepository.findByEmailAddress(tenantEmail);
        if(tenant != null && tenant.getRole() == Role.TENANT) {
            return tenant.getHomeAddress();
        }
        throw new IllegalAuthException("Home address not found");
    }
}
