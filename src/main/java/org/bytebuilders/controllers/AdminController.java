package org.bytebuilders.controllers;

import jakarta.validation.Valid;
import org.bytebuilders.dtos.Requests.*;
import org.bytebuilders.dtos.Responses.CloseAccountResponse;
import org.bytebuilders.dtos.Responses.ViewUserResponse;
import org.bytebuilders.dtos.Responses.ViewVisitorLogResponse;
import org.bytebuilders.services.role.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/closeAccount")
    public CloseAccountResponse closeAccount(@Valid @RequestBody CloseAccountRequest closeAccountRequest){
        return adminService.closeAccount(closeAccountRequest);
    }

    @GetMapping("/viewTenants")
    public List<ViewUserResponse> viewTenants(){
        return adminService.viewTenant();
    }

    @PostMapping("/viewTenantById")
    public List<ViewUserResponse> viewTenantById(@Valid @RequestBody ViewTenantByIdRequest request){
        return adminService.viewTenantById(request);
    }

    @GetMapping("/getAllSecurityPersonnel")
    public List<ViewUserResponse> getAllSecurityPersonnel(){
        return adminService.viewAllSecurityPersonnel();
    }

    @PostMapping("/getSecurityPersonnelById")
    public List<ViewUserResponse> getSecurityPersonnelById(@Valid @RequestBody ViewSecurityByIdRequest request){
        return adminService.viewSecurityPersonnelById(request);
    }

    @GetMapping("/getAllAdmins")
    public List<ViewUserResponse> getAllAdmins(){
        return adminService.viewAllAdmins();
    }

    @GetMapping("/getAdminById")
    public List<ViewUserResponse> getAdminById(@Valid @RequestBody ViewAdminByIdRequest request){
        return adminService.viewAdminById(request);
    }


    @GetMapping("/viewAllVisitors")
    public List<ViewVisitorLogResponse> viewAllVisitors(){
        return adminService.viewAllVisitors();
    }

    @PostMapping("/viewVisitorByOtpId")
    public List<ViewVisitorLogResponse> viewVisitorById(@Valid @RequestBody ViewVisitorLogRequest request){
        return adminService.viewVisitorLogById(request);
    }
}
