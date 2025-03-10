package org.bytebuilders.controllers;

import jakarta.validation.Valid;
import org.bytebuilders.dtos.Requests.LogInRequest;
import org.bytebuilders.dtos.Requests.OtpRequest;
import org.bytebuilders.dtos.Requests.SignUpRequest;
import org.bytebuilders.dtos.Requests.ValidateOtpRequest;
import org.bytebuilders.dtos.Responses.AuthResponse;
import org.bytebuilders.dtos.Responses.OtpResponse;
import org.bytebuilders.dtos.Responses.ValidateOtpResponse;
import org.bytebuilders.services.auth.AuthService;
import org.bytebuilders.services.role.SecurityService;
import org.bytebuilders.services.role.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private SecurityService securityService;

    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.signUp(signUpRequest);
    }

    @PostMapping("/login")
    public AuthResponse logIn(@Valid @RequestBody LogInRequest logInRequest){
        return authService.login(logInRequest);
    }

    @PostMapping("/generateOtp")
    public OtpResponse generateOtp(@Valid @RequestBody OtpRequest otpRequest){
        return tenantService.generateOtp(otpRequest);
    }

    @PostMapping("/validateOtp")
    public ValidateOtpResponse validateOtp(@Valid @RequestBody ValidateOtpRequest validateOtpRequest){
        return securityService.validateOtp(validateOtpRequest);
    }

    @PostMapping("/approveExit")
    public OtpResponse approveExit(@Valid @RequestBody OtpRequest otpRequest){
        return tenantService.approveExit(otpRequest);
    }
}
