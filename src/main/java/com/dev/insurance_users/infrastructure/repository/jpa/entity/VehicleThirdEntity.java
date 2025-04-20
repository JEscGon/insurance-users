package com.dev.insurance_users.infrastructure.repository.jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "thirds_vehicles")
@NoArgsConstructor
@AllArgsConstructor
public class VehicleThirdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_third_id", nullable = false)
    private UserThirdEntity userThird;

    @Column(unique = true , nullable = false)
    private String matricula;

    @Column(unique = true, nullable = false)
    private String aseguradora;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Long km;

    @Column(nullable = false)
    private String marca;

    @Column(name = "fecha_fabricacion", nullable = false)
    private LocalDate fechaFabricacion;

    @CreatedDate
    @Column(name = "date_of_registration" , nullable = false)
    private LocalDate dateOfRegistration;

    @LastModifiedDate
    @Column(name = "date_of_last_update" , nullable = false)
    private LocalDate dateOfLastUpdate;

}
