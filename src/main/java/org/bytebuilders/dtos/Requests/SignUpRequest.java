package org.bytebuilders.dtos.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    @NotBlank(message = "Email is required")
    @Email(message="Incorrect email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private String homeAddress;

    @NotBlank(message = "Role is required, (ADMIN, SECURITY, TENANT)")
    private String role;
}
