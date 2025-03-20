package org.bytebuilders.services.role;

import org.bytebuilders.data.model.*;
import org.bytebuilders.data.repositories.UsersRepository;
import org.bytebuilders.data.repositories.VisitorLogRepository;
import org.bytebuilders.data.repositories.VisitorPassRepository;
import org.bytebuilders.dtos.Requests.*;
import org.bytebuilders.dtos.Responses.*;
import org.bytebuilders.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AdminService implements RoleService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private VisitorLogRepository visitorLogs;

    @Autowired
    private VisitorPassRepository visitorPassRepository;

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }

    public List<ViewUserResponse> viewResident(){
       List <User> residents = usersRepository.findByRole(Role.RESIDENT);
       return residents.stream().map(resident -> new ViewUserResponse(resident.getId(), resident.getEmailAddress(), resident.getAccountStatus(), resident.getRole())).toList();
    }

    public ViewUserResponse viewResidentById(ViewResidentByIdRequest request){
        User resident = usersRepository.findById(request.getResidentId()).orElseThrow(()-> new UserNotFoundException("Resident not found"));
        return new ViewUserResponse(resident.getId(), resident.getEmailAddress(), resident.getAccountStatus(), resident.getRole());
    }


    public List<ViewUserResponse> viewAllSecurityPersonnel(){
        List<User> securityPersonnel = usersRepository.findByRole(Role.SECURITY);
        return securityPersonnel.stream().map(personnel -> new ViewUserResponse(personnel.getId(), personnel.getEmailAddress(), personnel.getAccountStatus(), personnel.getRole())).toList();
    }

    public ViewUserResponse viewSecurityPersonnelById(ViewSecurityByIdRequest request){
        User personnel = usersRepository.findById(request.getId()).orElseThrow(()-> new UserNotFoundException("Security Personnel not found"));
        return new ViewUserResponse(personnel .getId(), personnel .getEmailAddress(), personnel .getAccountStatus(), personnel .getRole());
    }


    public List<ViewUserResponse> viewAllAdmins(){
        List<User> admins = usersRepository.findByRole(Role.ADMIN);
        return admins.stream().map(admin -> new ViewUserResponse(admin.getId(), admin.getEmailAddress(), admin.getAccountStatus(), admin.getRole())).toList();
    }

    public ViewUserResponse viewAdminById(ViewAdminByIdRequest request){
        User admin = usersRepository.findById(request.getId()).orElseThrow(()-> new UserNotFoundException("Admin not found"));
        return new ViewUserResponse(admin .getId(), admin .getEmailAddress(), admin.getAccountStatus(), admin.getRole());
    }



    public List<ViewVisitorLogResponse> viewAllVisitors(){
        List<VisitorLog> visitors = visitorLogs.findAll();
        return visitors.stream().map(visitor -> new ViewVisitorLogResponse(visitor.getId(), visitor.getVisitorName(), visitor.getOtp(),convertDateFormatToString(visitor.getCreatedTime()), convertDateFormatToString(visitor.getExpirationTime()))).toList();
    }


    public ViewVisitorLogResponse viewVisitorLogById(ViewVisitorLogRequest request){
        VisitorLog visitor = visitorLogs.findById(request.getOtpId()).orElseThrow( ()-> new UserNotFoundException("Visitor not found"));
        return new ViewVisitorLogResponse(visitor.getId(), visitor.getVisitorName(), visitor.getOtp(), convertDateFormatToString(visitor.getCreatedTime()),convertDateFormatToString(visitor.getExpirationTime()));
    }

    public List<VisitorPassResponse> viewAllVisitorPass(){
        List<VisitorPass> visitorPasses = visitorPassRepository.findAll();
        return visitorPasses.stream().map( visitorPass -> new VisitorPassResponse(visitorPass.getVisitorId(), visitorPass.getVisitorName(), visitorPass.getPhoneNumber(), visitorPass.getResidentAddress(), visitorPass.getCheckInDateAndTime(), visitorPass.getCheckOutDateAndTime())).toList();
    }

    public VisitorPassResponse viewVisitorPassByPhoneNumber(VisitorPassByPhoneNumberRequest request){
        VisitorPass visitorPass = visitorPassRepository.findByPhoneNumber(request.getPhoneNumber()).orElseThrow(()-> new UserNotFoundException("Visitor not found"));
        return new VisitorPassResponse(visitorPass.getVisitorId(), visitorPass.getVisitorName(), visitorPass.getResidentAddress(), visitorPass.getPhoneNumber(), visitorPass.getCheckInDateAndTime(), visitorPass.getCheckOutDateAndTime());
    }



    public CloseAccountResponse closeAccount(CloseAccountRequest request) {
        User user = usersRepository.findById(request.getUserId()).orElseThrow( ()->  new UserNotFoundException("user not found"));
        user.setAccountStatus(AccountStatus.DEACTIVATED);
        usersRepository.save(user);
        CloseAccountResponse response = new CloseAccountResponse();
        response.setMessage("Account closed successfully");
        return response;
    }

    public ActivateAccountResponse activateAccount(ActivateAccountRequest request){
        User user = usersRepository.findById(request.getUserId()).orElseThrow( ()-> new UserNotFoundException("user not found"));
        user.setAccountStatus(AccountStatus.ACTIVE);
        usersRepository.save(user);
        ActivateAccountResponse response = new ActivateAccountResponse();
        response.setMessage("Account activated successfully");
        return response;
    }

    private String convertDateFormatToString(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a"));
    }
}
