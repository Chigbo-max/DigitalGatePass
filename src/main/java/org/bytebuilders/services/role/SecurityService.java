package org.bytebuilders.services.role;


import org.bytebuilders.data.model.*;
import org.bytebuilders.data.repositories.OtpLogsRepository;
import org.bytebuilders.data.repositories.VisitorLogRepository;
import org.bytebuilders.data.repositories.VisitorPassRepository;
import org.bytebuilders.dtos.Requests.ValidateOtpRequest;
import org.bytebuilders.dtos.Requests.VisitorPassRequest;
import org.bytebuilders.dtos.Responses.ValidateOtpResponse;
import org.bytebuilders.dtos.Responses.ViewVisitorLogResponse;
import org.bytebuilders.dtos.Responses.VisitorPassResponse;
import org.bytebuilders.exceptions.IllegalVisitorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class SecurityService implements RoleService {

    @Autowired
    private OtpLogsRepository otpLogsRepository;

    @Autowired
    private VisitorPassRepository visitorPassRepository;

    @Autowired
    private VisitorLogRepository visitorLogs;
    @Autowired
    private VisitorLogRepository visitorLogRepository;

    @Override
    public Role getRole() {
        return Role.SECURITY;
    }

    public ValidateOtpResponse validateOtp(ValidateOtpRequest request) {
        ValidateOtpResponse response = new ValidateOtpResponse();
        Optional<OtpLog> otpLog = otpLogsRepository.findByOtp(request.getOtp());

        String visitorId = otpLog.map(OtpLog::getId).orElse(null);
        String residentAddress = otpLog.map(OtpLog::getResidentAddress).orElse(null);

//        String visitorId;
//        if (otpLog.isPresent()) {
//            visitorId = otpLog.get().getId();
//        } else {
//            visitorId = null;
//        }


        if (otpLog.isEmpty()) {
            response.setMessage("OTP not found or invalid");
            response.setStatus(OtpValidationStatus.INVALID);
            return response;
        }

        if (LocalDateTime.now().isAfter(otpLog.get().getExpirationTime())) {
            response.setMessage("OTP is expired");
            response.setStatus(OtpValidationStatus.EXPIRED);
        }
        else {
            response.setMessage("OTP is valid");
            response.setStatus(OtpValidationStatus.VALID);
            response.setVisitorId("Visitor id: " + visitorId);
            response.setResidentAddress("Resident address: " + residentAddress);

        }

        return response;
    }

    public List<ViewVisitorLogResponse> viewAllVisitors(){
        List<VisitorLog> visitors = visitorLogs.findAll();
        return visitors.stream().map(visitor -> new ViewVisitorLogResponse(visitor.getId(), visitor.getVisitorName(), visitor.getOtp(),convertDateFormatToString(visitor.getCreatedTime()), convertDateFormatToString(visitor.getExpirationTime()))).toList();
    }


    public VisitorPassResponse checkIn(VisitorPassRequest request) {
        Optional<VisitorLog> visitorLog = visitorLogs.findByPhoneNumber(request.getPhoneNumber());

        if (visitorLog.isEmpty()) {
            throw new IllegalVisitorException("Visitor not found, The resident must generate an OTP for the visitor first");
        }

        VisitorLog visitor = visitorLog.get();

        if(visitor.getCheckinTime() != null) {
            throw new IllegalVisitorException("Visitor has already been checked in");
        }

        String checkInTime = getTimestamp().format(DateTimeFormatter.ofPattern("MMMM d, yyyy, hh:mm a"));
        visitor.setCheckinTime(checkInTime);
        visitorLogs.save(visitor);

        VisitorPass visitorPass = new VisitorPass();
        visitorPass.setVisitorName(request.getVisitorName());
        visitorPass.setPhoneNumber(visitor.getPhoneNumber());
        visitorPass.setCheckInDateAndTime(checkInTime);
        visitorPass.setResidentAddress(Optional.ofNullable(request.getResidentAddress()).orElse(""));
        visitorPassRepository.save(visitorPass);


        VisitorPassResponse visitorPassResponse = getCheckInVisitorPassResponse(request, visitorPass, checkInTime);

        return visitorPassResponse;
    }

    private static VisitorPassResponse getCheckInVisitorPassResponse(VisitorPassRequest request, VisitorPass visitorPass, String checkInTime) {
        VisitorPassResponse visitorPassResponse = new VisitorPassResponse();
        visitorPassResponse.setVisitorName(request.getVisitorName());
        visitorPassResponse.setVisitorPassId(visitorPass.getVisitorId());
        visitorPassResponse.setResidentAddress(request.getResidentAddress());
        visitorPassResponse.setCheckedInDateAndTime(checkInTime);
        return visitorPassResponse;
    }

    public VisitorPassResponse checkOut(VisitorPassRequest request) {
        Optional<VisitorPass> visitorPassOptional = visitorPassRepository.findByPhoneNumber(request.getPhoneNumber());

        if(visitorPassOptional.isEmpty()) {
            throw new IllegalVisitorException("The visitor has not been checked in.");
        }

        VisitorPass visitorPass = visitorPassOptional.get();
        if(visitorPass.getCheckOutDateAndTime() != null) {
            throw new IllegalVisitorException("The visitor has already been checked out");
        }

        String checkOutTime = getTimestamp().format(DateTimeFormatter.ofPattern("MMMM d, yyyy, hh:mm a"));
        visitorPass.setCheckOutDateAndTime(checkOutTime);
        visitorPassRepository.save(visitorPass);

        Optional<VisitorLog> visitorLogOptional = visitorLogs.findByPhoneNumber(request.getPhoneNumber());
        if(visitorLogOptional.isPresent()) {
            VisitorLog visitorLog = visitorLogOptional.get();
            visitorLog.setCheckoutTime(checkOutTime);
            visitorLogs.save(visitorLog);
        }

        VisitorPassResponse visitorPassResponse = getCheckOutVisitorPassResponse(request, visitorPass);

        return visitorPassResponse;
    }

    private static VisitorPassResponse getCheckOutVisitorPassResponse(VisitorPassRequest request, VisitorPass visitorPass) {
        VisitorPassResponse visitorPassResponse = new VisitorPassResponse();
        visitorPassResponse.setVisitorPassId(visitorPass.getVisitorId());
        visitorPassResponse.setVisitorName(request.getVisitorName());
        visitorPassResponse.setResidentAddress(Optional.ofNullable(request.getResidentAddress()).orElse(""));
        visitorPassResponse.setCheckedInDateAndTime(visitorPass.getCheckInDateAndTime());
        visitorPassResponse.setCheckedOutDateAndTime(visitorPass.getCheckOutDateAndTime());
        return visitorPassResponse;
    }

    private LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }

    private String convertDateFormatToString(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a"));
    }
}
