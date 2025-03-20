package com.dev.insurance_users.infrastructure.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "thirds_vehicles")
@NoArgsConstructor
@AllArgsConstructor
public class VehicleThirdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_third_id", nullable = false)
//    private UserThirdEntity user;
    @Column(name = "user_third_id", nullable = false)
    private Long userThirdId;

    @Column(unique = true , nullable = false)
    private String matricula;

    @Column(unique = true, nullable = false)
    private String aseguradora;

    @Column(nullable = false)
    private Long km;

    @Column(nullable = false)
    private String marca;

    @Column(name = "fecha_fabricacion",nullable = false)
    private LocalDate fechaFabricacion;

    @Column(name = "date_of_registration" ,nullable = true)
    private LocalDate dateOfRegistration;

    @Column(name = "date_of_last_update" ,nullable = true)
    private LocalDate dateOfLastUpdate;

}
