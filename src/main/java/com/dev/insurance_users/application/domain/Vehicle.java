package com.dev.insurance_users.application.domain;

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
    private Long idUser;
    private String matricula;
    private Long km;
    private String marca;
    private LocalDate fechaFabricacion;
    private LocalDate dateOfRegistration;
    private LocalDate dateOfLastUpdate;

}
