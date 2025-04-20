package com.dev.insurance_users.infrastructure.repository.jpa;

import com.dev.insurance_users.infrastructure.repository.jpa.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleJpaRepository extends JpaRepository<VehicleEntity, Long> {

    Optional<VehicleEntity> findById(Long id);

    Optional<VehicleEntity> findByMatricula(String matricula);

    List<VehicleEntity> findAll();

    void deleteById(Long id);

    void deleteByUserId(Long userId);




}
