package org.bytebuilders.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection="Users")
public class User {
    @Id
    private String id;
    private String emailAddress;
    private String password;
    private String homeAddress;
    private Role role;
    private AccountStatus accountStatus = AccountStatus.ACTIVE;
}
