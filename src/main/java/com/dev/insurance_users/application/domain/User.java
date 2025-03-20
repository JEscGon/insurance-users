package com.dev.insurance_users.application.domain;

import com.dev.insurance_users.infrastructure.entity.VehicleEntity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String dni;
    private String password;
    private String city;
    private String country;
    private String address;
    private LocalDate dateOfBirth;
    private LocalDate dateOfRegistration;
    private LocalDate dateOfLastUpdate;

    private List<Vehicle> vehicles;

}