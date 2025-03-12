package org.bytebuilders.services.auth;

import org.bytebuilders.data.model.User;
import org.bytebuilders.data.repositories.UsersRepository;
import org.bytebuilders.dtos.Requests.LogInRequest;
import org.bytebuilders.dtos.Requests.SignUpRequest;
import org.bytebuilders.dtos.Responses.AuthResponse;
import org.bytebuilders.enums.Role;
import org.bytebuilders.exceptions.IllegalAuthException;
import org.bytebuilders.services.role.RoleService;
import org.bytebuilders.utils.Mapper;
import org.bytebuilders.utils.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImplement implements AuthService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private Validations validations;

    @Autowired
    Mapper mapper;

    @Override
    public AuthResponse signUp(SignUpRequest signUpRequest) {
        validations.validateEmailAddress(signUpRequest.getEmail());
        User user = mapper.signUp(signUpRequest);

        if(user.getRole() == Role.TENANT && (user.getHomeAddress() == null || user.getHomeAddress().isBlank())){
            throw new IllegalAuthException("Home address is required");
        }

        usersRepository.save(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Sign up successful");
        authResponse.setRole(user.getRole().toString());
        return authResponse;
    }

    @Override
    public AuthResponse login(LogInRequest loginRequest) {
        RoleService roleService = mapper.login(loginRequest);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Login successful");
        authResponse.setRole(roleService.getRole().toString());
        return authResponse;
    }
}
