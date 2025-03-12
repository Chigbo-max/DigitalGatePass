package org.bytebuilders.services.role;

import org.bytebuilders.data.model.User;
import org.bytebuilders.data.model.VisitorLog;
import org.bytebuilders.data.repositories.UsersRepository;
import org.bytebuilders.data.repositories.VisitorLogRepository;
import org.bytebuilders.dtos.Requests.*;
import org.bytebuilders.dtos.Responses.CloseAccountResponse;
import org.bytebuilders.dtos.Responses.ViewUserResponse;
import org.bytebuilders.dtos.Responses.ViewVisitorLogResponse;
import org.bytebuilders.enums.AccountStatus;
import org.bytebuilders.enums.Role;
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

    public List<ViewUserResponse> viewTenant(){
       List <User> tenants = usersRepository.findByRole(Role.TENANT);
       return tenants.stream().map(tenant -> new ViewUserResponse(tenant.getId(), tenant.getEmailAddress(), tenant.getAccountStatus(), tenant.getRole())).toList();
    }

    public List<ViewUserResponse> viewTenantById(ViewTenantByIdRequest request){
        List<User> tenants = usersRepository.findById(request.getTenantId()).stream().toList();
        return tenants.stream().map(tenant -> new ViewUserResponse(tenant.getId(), tenant.getEmailAddress(), tenant.getAccountStatus(), tenant.getRole())).toList();
    }


    public List<ViewUserResponse> viewAllSecurityPersonnel(){
        List<User> securityPersonnel = usersRepository.findByRole(Role.SECURITY);
        return securityPersonnel.stream().map(personnel -> new ViewUserResponse(personnel.getId(), personnel.getEmailAddress(), personnel.getAccountStatus(), personnel.getRole())).toList();
    }

    public List<ViewUserResponse> viewSecurityPersonnelById(ViewSecurityByIdRequest request){
        List<User> securityPersonnel = usersRepository.findById(request.getId()).stream().toList();
        return securityPersonnel.stream().map(personnel -> new ViewUserResponse(personnel .getId(), personnel .getEmailAddress(), personnel .getAccountStatus(), personnel .getRole())).toList();
    }


    public List<ViewUserResponse> viewAllAdmins(){
        List<User> admins = usersRepository.findByRole(Role.ADMIN);
        return admins.stream().map(admin -> new ViewUserResponse(admin.getId(), admin.getEmailAddress(), admin.getAccountStatus(), admin.getRole())).toList();
    }

    public List<ViewUserResponse> viewAdminById(ViewAdminByIdRequest request){
        List<User> admins = usersRepository.findById(request.getId()).stream().toList();
        return admins.stream().map(admin -> new ViewUserResponse(admin .getId(), admin .getEmailAddress(), admin.getAccountStatus(), admin.getRole())).toList();
    }



    public List<ViewVisitorLogResponse> viewAllVisitors(){
        List<VisitorLog> visitors = visitorLogs.findAll();
        return visitors.stream().map(visitor -> new ViewVisitorLogResponse(visitor.getId(), visitor.getVisitorName(), visitor.getOtp(),convertDateFormatToString(visitor.getCreatedTime()), convertDateFormatToString(visitor.getExpirationTime()))).toList();
    }


    public List<ViewVisitorLogResponse> viewVisitorLogById(ViewVisitorLogRequest request){
        List<VisitorLog> visitors = visitorLogs.findById(request.getOtpId()).stream().toList();
        return visitors.stream().map(visitor -> new ViewVisitorLogResponse(visitor.getId(), visitor.getVisitorName(), visitor.getOtp(), convertDateFormatToString(visitor.getCreatedTime()),convertDateFormatToString(visitor.getExpirationTime()))).toList();
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
