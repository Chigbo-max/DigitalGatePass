package org.bytebuilders.dtos.Responses;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitorPassResponse {
    private String visitorPassId;
    private String visitorName;
    private String phoneNumber;
    private String residentAddress;
    private String checkedInDateAndTime;
    private String checkedOutDateAndTime;
}
