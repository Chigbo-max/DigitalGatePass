package org.bytebuilders.dtos.Requests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class VisitorPassRequest {
    private String visitorId;
    private String visitorName;
    private String phoneNumber;
    private String residentAddress;
}
