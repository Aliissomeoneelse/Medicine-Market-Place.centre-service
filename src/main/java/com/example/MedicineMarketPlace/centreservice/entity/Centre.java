package com.example.MedicineMarketPlace.centreservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = ("centre"))
public class Centre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer centreId;
    String name;
    String address;
    String description;
    String phone;
    Integer doctorId;
    Integer medicalServiceId;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
}
