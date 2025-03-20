package org.bytebuilders.data.repositories;

import org.bytebuilders.data.model.VisitorLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface VisitorLogRepository extends MongoRepository<VisitorLog, String> {

    Optional<VisitorLog> findById(String id);

    Optional<VisitorLog> findByPhoneNumber(String phoneNumber);

    List <VisitorLog> findAll();
}
