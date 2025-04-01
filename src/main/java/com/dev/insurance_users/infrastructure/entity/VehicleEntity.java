package com.dev.insurance_users.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicles")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user; //TODO: cambiar a userId  ¿?¿?

    @Column(unique = true , nullable = false)
    private String matricula;

    @Column(nullable = false)
    private Long km;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String color;

    @Column(name = "fecha_fabricacion", nullable = false)
    private LocalDate fechaFabricacion;

    @CreatedDate
    @Column(name = "date_of_registration" , nullable = false)
    private LocalDate dateOfRegistration;

    @LastModifiedDate
    @Column(name = "date_of_last_update" , nullable = false)
    private LocalDate dateOfLastUpdate;



}
