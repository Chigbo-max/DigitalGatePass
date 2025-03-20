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
    private String id;

    @NonNull
    private String visitorName;

    @NonNull
    private String otp;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String residentAddress;

    @NonNull
    private LocalDateTime createdTime;

    @NonNull
    private LocalDateTime expirationTime;

    private String checkinTime;

    private String checkoutTime;
}
