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
    @Indexed(expireAfter = "0s")
    private LocalDateTime expirationTime;

}
