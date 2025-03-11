package org.bytebuilders.dtos.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Incorrect email pattern")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
