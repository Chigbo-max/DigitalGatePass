package org.bytebuilders.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class VisitorPass {
    @Id
    private String visitorId;
    private String phoneNumber;
    private String visitorName;
    private String checkInDateAndTime;
    private String checkOutDateAndTime;
    private String residentAddress;
}
