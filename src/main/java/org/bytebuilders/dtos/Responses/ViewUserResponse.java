package org.bytebuilders.dtos.Responses;

import lombok.*;
import org.bytebuilders.data.model.AccountStatus;
import org.bytebuilders.data.model.Role;

@Getter
@Setter
@AllArgsConstructor
public class ViewUserResponse {
    private String id;
    private String email;
    private AccountStatus status;

    private Role role;
}
