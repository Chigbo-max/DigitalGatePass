package org.bytebuilders.dtos.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInRequest {
    private String email;
    private String password;
}
