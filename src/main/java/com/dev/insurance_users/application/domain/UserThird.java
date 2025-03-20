package com.dev.insurance_users.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserThird {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String dni;
    private String city;
    private String country;
    private String address;
    private LocalDate dateOfBirth;
    private LocalDate dateOfRegistration;
    private LocalDate dateOfLastUpdate;

    private List<VehicleThird> vehicles;

}
