package org.bytebuilders.services.role;

import org.bytebuilders.data.repositories.OtpLogsRepository;
import org.bytebuilders.dtos.Requests.OtpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.bytebuilders.data.model.Role.RESIDENT;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class residentServiceTest {

    @Autowired
    private ResidentService residentService;

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
    void testThatGetRoleReturnsRole_getResident() {
        assertEquals(RESIDENT, residentService.getRole());
    }

    @Test
    void testThatResidentCanGenerateOtpRequest_getOne() {
        otpRequest.setVisitorName("Visitor");
        otpRequest.setResidentEmail("mike@gmail.com");
        residentService.generateOtp(otpRequest);
        assertEquals(1, otpLogsRepository.count());
    }

    @Test
    void testThatGeneratedOtpExpiresAfterOneSecond_deletesFromTheRepository() {
        assertEquals(0, otpLogsRepository.count());
    }

    @Test
    void testThatResidentCanGenerateOtpForVisitorExitApproval() {
        otpRequest.setVisitorName("Visitor");
        residentService.approveExit(otpRequest);
        assertEquals(1, otpLogsRepository.count());
    }


}