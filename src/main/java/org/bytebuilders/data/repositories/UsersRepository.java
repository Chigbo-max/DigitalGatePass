package org.bytebuilders.data.repositories;

import org.bytebuilders.data.model.User;
import org.bytebuilders.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsersRepository extends MongoRepository<User, String> {
    User findByEmailAddress(String email);

    boolean existsByEmailAddress(String emailAddress);

    List<User> findByRole(Role role);
}
