package org.bytebuilders.services.role;

import org.bytebuilders.data.model.User;
import org.bytebuilders.data.repositories.UsersRepository;
import org.bytebuilders.dtos.Requests.CloseAccountRequest;
import org.bytebuilders.dtos.Responses.CloseAccountResponse;
import org.bytebuilders.dtos.Responses.ViewUserResponse;
import org.bytebuilders.enums.AccountStatus;
import org.bytebuilders.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements RoleService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }

    public List<ViewUserResponse> viewTenant(){
       List <User> tenants = usersRepository.findByRole(Role.TENANT);
       return tenants.stream().map(tenant -> new ViewUserResponse(tenant.getId(), tenant.getEmailAddress(), tenant.getAccountStatus(), tenant.getRole())).toList();
    }

    public CloseAccountResponse closeAccount(CloseAccountRequest request) {
        User user = usersRepository.findById(request.getUserId()).orElseThrow( ()->  new IllegalArgumentException("user not found"));
        user.setAccountStatus(AccountStatus.DEACTIVATED);
        usersRepository.save(user);
        CloseAccountResponse response = new CloseAccountResponse();
        response.setMessage("Account closed successfully");
        return response;
    }
}
