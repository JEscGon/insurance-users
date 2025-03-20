package com.dev.insurance_users.application.domain;

import com.dev.insurance_users.infrastructure.entity.UserEntity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private Long id;
    private String matricula;
    private Long km;
    private String marca;
    private LocalDate fechaFabricacion;
    private LocalDate dateOfRegistration;
    private LocalDate dateOfLastUpdate;

    private Integer userId;


}
