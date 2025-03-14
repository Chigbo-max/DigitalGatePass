package org.bytebuilders.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Document(collection="VistorLog")
public class VisitorLog {
    @Id
    String id;

    @NonNull
    String visitorName;

    @NonNull
    String otp;

    @NonNull
    String residentAddress;

    @NonNull
    LocalDateTime createdTime;

    @NonNull
    LocalDateTime expirationTime;
}
