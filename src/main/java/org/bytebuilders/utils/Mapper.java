package org.bytebuilders.utils;


import org.bytebuilders.data.model.User;
import org.bytebuilders.data.repositories.UsersRepository;
import org.bytebuilders.dtos.Requests.LogInRequest;
import org.bytebuilders.dtos.Requests.SignUpRequest;
import org.bytebuilders.enums.Role;
import org.bytebuilders.exceptions.IllegalAuthException;
import org.bytebuilders.services.role.RoleService;
import org.bytebuilders.services.role.RoleServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleServiceFactory roleServiceFactory;

    public User signUp(SignUpRequest request){
        User user = new User();
        user.setEmailAddress(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(validateRole(request.getRole()));
        return user;
    }

    private Role validateRole(String role){
        try{
            return Role.valueOf(role.toUpperCase());
        }
        catch(IllegalArgumentException e){
            throw new IllegalAuthException("Invalid role: " + role);
        }
    }

    public RoleService login(LogInRequest request){
        User user = usersRepository.findByEmailAddress(request.getEmail());
        if(user == null || !user.getPassword().equals(request.getPassword())){
            throw new IllegalAuthException("Invalid credentials");
        }

        return roleServiceFactory.getService(user);

    }



}
