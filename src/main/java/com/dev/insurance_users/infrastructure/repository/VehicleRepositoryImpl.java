package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.Vehicle;
import com.dev.insurance_users.application.exception.VehicleNotFoundException;
import com.dev.insurance_users.application.repository.VehicleRepository;

import com.dev.insurance_users.infrastructure.repository.jpa.entity.UserEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.VehicleEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserJpaRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.VehicleJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.VehicleMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class VehicleRepositoryImpl implements VehicleRepository {

    private final VehicleMapper vehicleMapper;
    private final VehicleJpaRepository vehicleJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public void save(Vehicle vehicle) {
        VehicleEntity vehicleEntity = vehicleMapper.fromDomainToEntity(vehicle);
        if(vehicle.getId() == null) { // nuevo vehículo
            UserEntity userEntity = userJpaRepository.findById(Long.valueOf(vehicle.getUserId()))
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + vehicle.getUserId()));
            vehicleEntity.setUser(userEntity);
            vehicleJpaRepository.save(vehicleEntity);
        } else { // actualización
            VehicleEntity existingVehicle = vehicleJpaRepository.findById(vehicle.getId())
                    .orElseThrow(() -> new VehicleNotFoundException("Vehículo no encontrado con id: " + vehicle.getId()));
            vehicleMapper.updateVehicleFromExisting(existingVehicle, vehicleEntity);
            vehicleEntity.setUser(existingVehicle.getUser());
            vehicleJpaRepository.save(existingVehicle);
        }
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return vehicleJpaRepository.findById(id)
                .map(vehicleMapper::fromEntityToDomain);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleJpaRepository.findAll().stream()
                .map(vehicleMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        vehicleJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Vehicle> findByMatricula(String matricula) {
        return vehicleJpaRepository.findByMatricula(matricula)
                .map(vehicleMapper::fromEntityToDomain);
    }

    @Override
    public void deleteByUserId(Long userId) { vehicleJpaRepository.deleteByUserId(userId); }

}
