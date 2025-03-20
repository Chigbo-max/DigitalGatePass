package org.bytebuilders.controllers;

import jakarta.validation.Valid;
import org.bytebuilders.dtos.Requests.*;
import org.bytebuilders.dtos.Responses.*;
import org.bytebuilders.services.role.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/admin/closeAccount")
    public CloseAccountResponse closeAccount(@Valid @RequestBody CloseAccountRequest closeAccountRequest){
        return adminService.closeAccount(closeAccountRequest);
    }

    @PostMapping("/admin/activateAccount")
    public ActivateAccountResponse activateAccount(@Valid @RequestBody ActivateAccountRequest activateAccountRequest){
        return adminService.activateAccount(activateAccountRequest);
    }

    @GetMapping("/admin/viewResidents")
    public List<ViewUserResponse> viewResidents(){
        return adminService.viewResident();
    }

    @PostMapping("/admin/viewResidentById")
    public ViewUserResponse viewResidentById(@Valid @RequestBody ViewResidentByIdRequest request){
        return adminService.viewResidentById(request);
    }

    @GetMapping("/admin/getAllSecurityPersonnel")
    public List<ViewUserResponse> getAllSecurityPersonnel(){
        return adminService.viewAllSecurityPersonnel();
    }

    @PostMapping("/admin/getSecurityPersonnelById")
    public ViewUserResponse getSecurityPersonnelById(@Valid @RequestBody ViewSecurityByIdRequest request){
        return adminService.viewSecurityPersonnelById(request);
    }

    @GetMapping("/admin/getAllAdmins")
    public List<ViewUserResponse> getAllAdmins(){
        return adminService.viewAllAdmins();
    }

    @GetMapping("/admin/getAdminById")
    public ViewUserResponse getAdminById(@Valid @RequestBody ViewAdminByIdRequest request){
        return adminService.viewAdminById(request);
    }


    @GetMapping("/admin/viewAllVisitors")
    public List<ViewVisitorLogResponse> viewAllVisitors(){
        return adminService.viewAllVisitors();
    }

    @PostMapping("/admin/viewVisitorByOtpId")
    public ViewVisitorLogResponse viewVisitorById(@Valid @RequestBody ViewVisitorLogRequest request){
        return adminService.viewVisitorLogById(request);
    }

    @GetMapping("/admin/viewAllVisitorPass")
    public List<VisitorPassResponse> viewAllVisitorPass(){
        return adminService.viewAllVisitorPass();
    }

    @PostMapping("/admin/getVisitorPassByPhoneNumber")
    public VisitorPassResponse getVisitorPassByPhoneNumber(@Valid @RequestBody VisitorPassByPhoneNumberRequest request){
        return adminService.viewVisitorPassByPhoneNumber(request);
    }

}
