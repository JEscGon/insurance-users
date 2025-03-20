package com.dev.insurance_users.infrastructure.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "third_users")
@NoArgsConstructor
@AllArgsConstructor
public class UserThirdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String dni;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String address;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "date_of_registration" ,nullable = true)
    private LocalDate dateOfRegistration;
    @Column(name = "date_of_last_update" ,nullable = true)
    private LocalDate dateOfLastUpdate;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<VehicleThirdEntity> vehicles;

}
