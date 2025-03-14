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

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/closeAccount")
    public CloseAccountResponse closeAccount(@Valid @RequestBody CloseAccountRequest closeAccountRequest){
        return adminService.closeAccount(closeAccountRequest);
    }

    @GetMapping("/viewResidents")
    public List<ViewUserResponse> viewResidents(){
        return adminService.viewResident();
    }

    @PostMapping("/viewResidentById")
    public ViewUserResponse viewResidentById(@Valid @RequestBody ViewResidentByIdRequest request){
        return adminService.viewResidentById(request);
    }

    @GetMapping("/getAllSecurityPersonnel")
    public List<ViewUserResponse> getAllSecurityPersonnel(){
        return adminService.viewAllSecurityPersonnel();
    }

    @PostMapping("/getSecurityPersonnelById")
    public ViewUserResponse getSecurityPersonnelById(@Valid @RequestBody ViewSecurityByIdRequest request){
        return adminService.viewSecurityPersonnelById(request);
    }

    @GetMapping("/getAllAdmins")
    public List<ViewUserResponse> getAllAdmins(){
        return adminService.viewAllAdmins();
    }

    @GetMapping("/getAdminById")
    public ViewUserResponse getAdminById(@Valid @RequestBody ViewAdminByIdRequest request){
        return adminService.viewAdminById(request);
    }


    @GetMapping("/viewAllVisitors")
    public List<ViewVisitorLogResponse> viewAllVisitors(){
        return adminService.viewAllVisitors();
    }

    @PostMapping("/viewVisitorByOtpId")
    public ViewVisitorLogResponse viewVisitorById(@Valid @RequestBody ViewVisitorLogRequest request){
        return adminService.viewVisitorLogById(request);
    }
}
