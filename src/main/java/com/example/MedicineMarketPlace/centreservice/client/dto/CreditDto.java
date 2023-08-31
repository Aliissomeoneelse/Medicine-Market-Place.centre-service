package com.example.MedicineMarketPlace.centreservice.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditDto {
    Integer creditId;
    Double amount;
    String payed;
    String pay;
    LocalDateTime issueDate;
    LocalDateTime expireDate;
    String period;
    String medService;
    String title;
    String medicalCenterName;
    String creditStatus;
    Boolean successful;
    Integer userId;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
}
