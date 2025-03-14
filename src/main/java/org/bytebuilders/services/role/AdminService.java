package org.bytebuilders.services.role;

import org.bytebuilders.data.model.User;
import org.bytebuilders.data.model.VisitorLog;
import org.bytebuilders.data.repositories.UsersRepository;
import org.bytebuilders.data.repositories.VisitorLogRepository;
import org.bytebuilders.dtos.Requests.*;
import org.bytebuilders.dtos.Responses.CloseAccountResponse;
import org.bytebuilders.dtos.Responses.ViewUserResponse;
import org.bytebuilders.dtos.Responses.ViewVisitorLogResponse;
import org.bytebuilders.data.model.AccountStatus;
import org.bytebuilders.data.model.Role;
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

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }

    public List<ViewUserResponse> viewResident(){
       List <User> residents = usersRepository.findByRole(Role.RESIDENT);
       return residents.stream().map(resident -> new ViewUserResponse(resident.getId(), resident.getEmailAddress(), resident.getAccountStatus(), resident.getRole())).toList();
    }

    public ViewUserResponse viewResidentById(ViewResidentByIdRequest request){
        User resident = usersRepository.findById(request.getResidentId()).orElseThrow(()-> new IllegalArgumentException("Resident not found"));
        return new ViewUserResponse(resident.getId(), resident.getEmailAddress(), resident.getAccountStatus(), resident.getRole());
    }


    public List<ViewUserResponse> viewAllSecurityPersonnel(){
        List<User> securityPersonnel = usersRepository.findByRole(Role.SECURITY);
        return securityPersonnel.stream().map(personnel -> new ViewUserResponse(personnel.getId(), personnel.getEmailAddress(), personnel.getAccountStatus(), personnel.getRole())).toList();
    }

    public ViewUserResponse viewSecurityPersonnelById(ViewSecurityByIdRequest request){
        User personnel = usersRepository.findById(request.getId()).orElseThrow(()-> new IllegalArgumentException("Security Personnel not found"));
        return new ViewUserResponse(personnel .getId(), personnel .getEmailAddress(), personnel .getAccountStatus(), personnel .getRole());
    }


    public List<ViewUserResponse> viewAllAdmins(){
        List<User> admins = usersRepository.findByRole(Role.ADMIN);
        return admins.stream().map(admin -> new ViewUserResponse(admin.getId(), admin.getEmailAddress(), admin.getAccountStatus(), admin.getRole())).toList();
    }

    public ViewUserResponse viewAdminById(ViewAdminByIdRequest request){
        User admin = usersRepository.findById(request.getId()).orElseThrow(()-> new IllegalArgumentException("Admin not found"));
        return new ViewUserResponse(admin .getId(), admin .getEmailAddress(), admin.getAccountStatus(), admin.getRole());
    }



    public List<ViewVisitorLogResponse> viewAllVisitors(){
        List<VisitorLog> visitors = visitorLogs.findAll();
        return visitors.stream().map(visitor -> new ViewVisitorLogResponse(visitor.getId(), visitor.getVisitorName(), visitor.getOtp(),convertDateFormatToString(visitor.getCreatedTime()), convertDateFormatToString(visitor.getExpirationTime()))).toList();
    }


    public ViewVisitorLogResponse viewVisitorLogById(ViewVisitorLogRequest request){
        VisitorLog visitor = visitorLogs.findById(request.getOtpId()).orElseThrow( ()-> new IllegalArgumentException("Visitor not found"));
        return new ViewVisitorLogResponse(visitor.getId(), visitor.getVisitorName(), visitor.getOtp(), convertDateFormatToString(visitor.getCreatedTime()),convertDateFormatToString(visitor.getExpirationTime()));
    }



    public CloseAccountResponse closeAccount(CloseAccountRequest request) {
        User user = usersRepository.findById(request.getUserId()).orElseThrow( ()->  new IllegalArgumentException("user not found"));
        user.setAccountStatus(AccountStatus.DEACTIVATED);
        usersRepository.save(user);
        CloseAccountResponse response = new CloseAccountResponse();
        response.setMessage("Account closed successfully");
        return response;
    }

    private String convertDateFormatToString(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a"));
    }
}
