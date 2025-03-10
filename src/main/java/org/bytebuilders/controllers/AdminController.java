package org.bytebuilders.controllers;

import jakarta.validation.Valid;
import org.bytebuilders.dtos.Requests.CloseAccountRequest;
import org.bytebuilders.dtos.Responses.CloseAccountResponse;
import org.bytebuilders.dtos.Responses.ViewUserResponse;
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
}
