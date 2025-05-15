package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.Vehicle;
import com.dev.insurance_users.application.exception.ResourceNotFoundException;
import com.dev.insurance_users.application.repository.VehicleRepository;

import com.dev.insurance_users.infrastructure.repository.jpa.entity.UserEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.VehicleEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserJpaRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.VehicleJpaRepository;
import com.dev.insurance_users.infrastructure.rest.mapper.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            VehicleEntity vehicleEntity = vehicleMapper.fromDomainToEntity(vehicle);
            if (vehicle.getId() == null) { // nuevo vehículo
                UserEntity userEntity = userJpaRepository.findById(Long.valueOf(vehicle.getUserId()))
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + vehicle.getUserId()));
                vehicleEntity.setUser(userEntity);
                vehicleJpaRepository.save(vehicleEntity);
            } else { // actualización
                VehicleEntity existingVehicle = vehicleJpaRepository.findById(vehicle.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado con id: " + vehicle.getId()));
                vehicleMapper.updateVehicleFromExisting(existingVehicle, vehicleEntity);
                vehicleEntity.setUser(existingVehicle.getUser());
                vehicleJpaRepository.save(existingVehicle);
            }
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Error de integridad de datos al guardar el vehículo. Detalles: " + e.getMessage());
        }
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        try {
            return vehicleJpaRepository.findById(id)
                    .map(vehicleMapper::fromEntityToDomain);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al buscar el vehículo con id: " + id + ". Detalles: " + e.getMessage());
        }
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleJpaRepository.findAll().stream()
                .map(vehicleMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        try {
            vehicleJpaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("No se encontró el vehiculo con id: " + id + " para eliminar.");
        }
    }

    @Override
    public Optional<Vehicle> findByMatricula(String matricula) {
        try {
            return vehicleJpaRepository.findByMatricula(matricula)
                    .map(vehicleMapper::fromEntityToDomain);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al buscar el vehículo con matrícula: " + matricula + ". Detalles: " + e.getMessage());
        }
    }

    @Override
    public void deleteByUserId(Long userId) { vehicleJpaRepository.deleteByUserId(userId); }

    @Override
    public List<Vehicle> findByUserId(Long userId) {
        try {
            return vehicleJpaRepository.findByUserId(userId).stream()
                    .map(vehicleMapper::fromEntityToDomain)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al buscar vehículos para el usuario con id: " + userId + ". Detalles: " + e.getMessage());
        }
    }

}
