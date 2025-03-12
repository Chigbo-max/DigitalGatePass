package org.bytebuilders.services.role;

import org.bytebuilders.data.repositories.OtpLogsRepository;
import org.bytebuilders.dtos.Requests.OtpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.bytebuilders.data.model.Role.TENANT;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TenantServiceTest {

    @Autowired
    private TenantService tenantService;

    private OtpRequest otpRequest;

    @Autowired
    private OtpLogsRepository otpLogsRepository;


    @BeforeEach
    void setUp() {
        otpRequest = new OtpRequest();
    }
//
//    @BeforeEach
//    void tearDown() {
//        otpLogsRepository.deleteAll();
//    }

    @Test
    void testThatGetRoleReturnsRole_getTenant() {
        assertEquals(TENANT, tenantService.getRole());
    }

    @Test
    void testThatTenantCanGenerateOtpRequest_getOne() {
        otpRequest.setVisitorName("Visitor");
        otpRequest.setTenantEmail("mike@gmail.com");
        tenantService.generateOtp(otpRequest);
        assertEquals(1, otpLogsRepository.count());
    }

    @Test
    void testThatGeneratedOtpExpiresAfterOneSecond_deletesFromTheRepository() {
        assertEquals(0, otpLogsRepository.count());
    }

    @Test
    void testThatTenantCanGenerateOtpForVisitorExitApproval() {
        otpRequest.setVisitorName("Visitor");
        tenantService.approveExit(otpRequest);
        assertEquals(1, otpLogsRepository.count());
    }


}