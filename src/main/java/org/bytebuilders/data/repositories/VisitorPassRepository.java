package org.bytebuilders.data.repositories;

import org.bytebuilders.data.model.VisitorPass;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VisitorPassRepository extends MongoRepository<VisitorPass, String> {
    Optional<VisitorPass> findByVisitorId(String visitorId);
    Optional<VisitorPass> findByPhoneNumber(String phoneNumber);
}
