package com.dev.insurance_users.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicles")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;

    @Column(unique = true , nullable = false)
    private String matricula;

    @Column(nullable = false)
    private Long km;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String color;

    @Column(name = "fecha_fabricacion",nullable = false)
    private LocalDate fechaFabricacion;
    @Column(name = "date_of_registration" ,nullable = true)
    private LocalDate dateOfRegistration;
    @Column(name = "date_of_last_update" ,nullable = true)
    private LocalDate dateOfLastUpdate;



}
