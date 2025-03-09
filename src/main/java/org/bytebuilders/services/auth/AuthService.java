package org.bytebuilders.services.auth;

import org.bytebuilders.data.model.User;
import org.bytebuilders.dtos.Requests.LogInRequest;
import org.bytebuilders.dtos.Requests.SignUpRequest;
import org.bytebuilders.dtos.Responses.AuthResponse;
import org.bytebuilders.enums.Role;
import org.bytebuilders.services.role.RoleService;

public interface AuthService {
    AuthResponse signUp(SignUpRequest signUpRequest);
    AuthResponse login(LogInRequest logInRequest);

}
