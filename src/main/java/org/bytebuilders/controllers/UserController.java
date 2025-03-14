package org.bytebuilders.controllers;

import jakarta.validation.Valid;
import org.bytebuilders.dtos.Requests.*;
import org.bytebuilders.dtos.Responses.*;
import org.bytebuilders.services.auth.AuthService;
import org.bytebuilders.services.role.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ResidentService residentService;

    //Api documentation
    //https://documenter.getpostman.com/view/42348839/2sAYk8vioq

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
        return residentService.generateOtp(otpRequest);
    }


    @PostMapping("/approveExit")
    public OtpResponse approveExit(@Valid @RequestBody OtpRequest otpRequest){
        return residentService.approveExit(otpRequest);
    }



}
