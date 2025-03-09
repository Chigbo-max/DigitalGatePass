package org.bytebuilders.data.repositories;

import jakarta.validation.constraints.NotBlank;
import org.bytebuilders.data.model.OtpLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtpLogsRepository extends MongoRepository<OtpLog, String> {
   Optional <OtpLog> findByOtp(String otp);
}
