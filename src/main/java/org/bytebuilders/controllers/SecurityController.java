package org.bytebuilders.controllers;

import jakarta.validation.Valid;
import org.bytebuilders.dtos.Requests.ValidateOtpRequest;
import org.bytebuilders.dtos.Responses.ValidateOtpResponse;
import org.bytebuilders.services.role.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @PostMapping("/validateOtp")
    public ValidateOtpResponse validateOtp(@Valid @RequestBody ValidateOtpRequest validateOtpRequest){
        return securityService.validateOtp(validateOtpRequest);
    }
}
