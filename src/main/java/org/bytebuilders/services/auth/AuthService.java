package org.bytebuilders.services.auth;

import org.bytebuilders.dtos.Requests.LogInRequest;
import org.bytebuilders.dtos.Requests.SignUpRequest;
import org.bytebuilders.dtos.Responses.AuthResponse;

public interface AuthService {
    AuthResponse signUp(SignUpRequest signUpRequest);
    AuthResponse login(LogInRequest logInRequest);

}
