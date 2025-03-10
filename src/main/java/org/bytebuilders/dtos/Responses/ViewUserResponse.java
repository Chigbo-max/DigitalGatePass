package org.bytebuilders.dtos.Responses;

import lombok.*;
import org.bytebuilders.enums.AccountStatus;
import org.bytebuilders.enums.Role;

@Getter
@Setter
@AllArgsConstructor
public class ViewUserResponse {
    private String id;
    private String email;
    private AccountStatus status;

    private Role role;
}
