package com.dev.insurance_users.infrastructure.repository.jpa;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String password;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String address;
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    @Column(name = "date_of_registration", nullable = false)
    private LocalDate dateOfRegistration;

    public UserEntity(String name, String surname, String phone, String email,
                      String password, LocalDate dateOfBirth, LocalDate dateOfRegistration,
                      String address, String city, String country, String dni) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = LocalDate.now();
        this.address = address;
        this.city = city;
        this.country = country;
        this.dni = dni;
    }

    public UserEntity() {
    }


}
