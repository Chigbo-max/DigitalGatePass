package org.bytebuilders.services.role;

import org.bytebuilders.dtos.Requests.ValidateOtpRequest;
import org.bytebuilders.dtos.Responses.ValidateOtpResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.bytebuilders.data.model.Role.SECURITY;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecurityServiceTest {


    @Autowired
    private SecurityService securityService;
    @Autowired
    private ResidentService tenantService;

    private ValidateOtpRequest validateOtpRequest;

    private ValidateOtpResponse validateOtpResponse;

    @BeforeEach
    void setUp() {
        validateOtpRequest = new ValidateOtpRequest();
//        validateOtpResponse = new ValidateOtpResponse();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testThatGetRoleReturnsRole_getTenant() {
        assertEquals(SECURITY, securityService.getRole());
    }

    @Test
    void testThatSecurityValidatesOtp(){
        validateOtpRequest.setOtp("VE333");
        validateOtpResponse = securityService.validateOtp(validateOtpRequest);
        assertEquals("OTP not found or invalid", validateOtpResponse.getMessage());
    }

    @Test
    void validateOtp() {
    }
}