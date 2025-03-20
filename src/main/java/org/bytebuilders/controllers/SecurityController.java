package org.bytebuilders.controllers;

import jakarta.validation.Valid;
import org.bytebuilders.dtos.Requests.ValidateOtpRequest;
import org.bytebuilders.dtos.Requests.VisitorPassRequest;
import org.bytebuilders.dtos.Responses.ValidateOtpResponse;
import org.bytebuilders.dtos.Responses.VisitorPassResponse;
import org.bytebuilders.services.role.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @PostMapping("/security/validateOtp")
    public ValidateOtpResponse validateOtp(@Valid @RequestBody ValidateOtpRequest validateOtpRequest){
        return securityService.validateOtp(validateOtpRequest);
    }

    @PostMapping("/security/checkIn")
    public VisitorPassResponse checkIn(@Valid @RequestBody VisitorPassRequest visitorPassRequest){
        return securityService.checkIn(visitorPassRequest);
    }

    @PostMapping("/security/checkOut")
    public VisitorPassResponse checkOut(@Valid @RequestBody VisitorPassRequest visitorPassRequest){
        return securityService.checkOut(visitorPassRequest);
    }
}
