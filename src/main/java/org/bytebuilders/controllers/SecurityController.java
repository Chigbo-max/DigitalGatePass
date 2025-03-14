package org.bytebuilders.controllers;

import jakarta.validation.Valid;
import org.bytebuilders.dtos.Requests.ValidateOtpRequest;
import org.bytebuilders.dtos.Responses.ValidateOtpResponse;
import org.bytebuilders.services.role.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
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
