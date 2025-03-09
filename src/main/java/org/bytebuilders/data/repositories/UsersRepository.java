package org.bytebuilders.data.repositories;

import org.bytebuilders.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, String> {
    User findByEmailAddress(String email);

    boolean existsByEmailAddress(String emailAddress);
}
