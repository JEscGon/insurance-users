package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.application.repository.VehicleThirdRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.UserThirdEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.VehicleThirdEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserThirdJpaRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.VehicleThirdJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.VehicleThirdMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class VehicleThirdRepositoryImpl implements VehicleThirdRepository {

    private final VehicleThirdMapper vehicleThirdMapper;
    private final VehicleThirdJpaRepository vehicleThirdJpaRepository;
    private final UserThirdJpaRepository userThirdJpaRepository;

    @Override
    public void save(VehicleThird vehicle) {
        VehicleThirdEntity vehicleThirdEntity = vehicleThirdMapper.fromDomainToEntity(vehicle);
        if(vehicle.getId() == null) { // nuevo vehículo
            UserThirdEntity userThirdEntity = userThirdJpaRepository.findById(vehicle.getUserThirdId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + vehicle.getUserThirdId()));
            vehicleThirdEntity.setUserThird(userThirdEntity);
            vehicleThirdJpaRepository.save(vehicleThirdEntity);
        } else { // actualización
            VehicleThirdEntity existingVehicle = vehicleThirdJpaRepository.findById(vehicle.getId())
                    .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con id: " + vehicle.getId()));
            vehicleThirdMapper.updateVehicleThirdFromExisting(existingVehicle, vehicleThirdEntity);
            existingVehicle.setUserThird(existingVehicle.getUserThird()); // mantiene la referencia original del usuario
            vehicleThirdJpaRepository.save(existingVehicle);
        }
    }

    @Override
    public Optional<VehicleThird> findById(Long id) {
        return vehicleThirdJpaRepository.findById(id)
                .map(vehicleThirdMapper::fromEntityToDomain);
    }

    @Override
    public List<VehicleThird> findAll() {
        return vehicleThirdJpaRepository.findAll().stream()
                .map(vehicleThirdMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        vehicleThirdJpaRepository.deleteById(id);
    }

    @Override
    public Optional<VehicleThird> findByMatricula(String matricula) {
        return vehicleThirdJpaRepository.findByMatricula(matricula)
                .map(vehicleThirdMapper::fromEntityToDomain);
    }
}
