package com.dev.insurance_users.infrastructure.repository.jpa;

import com.dev.insurance_users.infrastructure.entity.VehicleThirdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleThirdJpaRepository extends JpaRepository<VehicleThirdEntity, Long> {

    Optional<VehicleThirdEntity> findById(Long id);

    List<VehicleThirdEntity> findAll();

    void deleteById(Long id);

}
