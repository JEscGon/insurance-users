package com.dev.insurance_users.infrastructure.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "third_users")
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

    @CreatedDate
    @Column(name = "date_of_registration" ,nullable = true)
    private LocalDate dateOfRegistration;
    @LastModifiedDate
    @Column(name = "date_of_last_update" ,nullable = true)
    private LocalDate dateOfLastUpdate;

    @OneToMany(mappedBy = "userThird")
    private List<VehicleThirdEntity> vehicles;

}

