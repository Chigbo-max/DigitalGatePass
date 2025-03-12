package org.bytebuilders.data.repositories;

import org.bytebuilders.data.model.User;
import org.bytebuilders.data.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends MongoRepository<User, String> {
    User findByEmailAddress(String email);

    boolean existsByEmailAddress(String emailAddress);

    List<User> findByRole(Role role);
    Optional<User> findById(String id);
}
