package org.bytebuilders.data.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection="Otp_Log")
public class OtpLog {
    @Id
    String id;

    @NonNull
    String visitorName;

    @NonNull
    String otp;

    @NonNull
    LocalDateTime createdTime;

    @Indexed(expireAfter = "0s")
    @NonNull
    LocalDateTime expirationTime;

}
