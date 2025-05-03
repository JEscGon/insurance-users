package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.application.exception.DuplicateResourceException;
import com.dev.insurance_users.application.exception.ResourceNotFoundException;
import com.dev.insurance_users.application.repository.VehicleThirdRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.UserThirdEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.VehicleThirdEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserThirdJpaRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.VehicleThirdJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.VehicleThirdMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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
        if (vehicle.getId() == null) { // nuevo vehículo
            if (vehicleThirdJpaRepository.findByMatricula(vehicle.getMatricula()).isPresent()) {
                throw new DuplicateResourceException("Ya existe un vehiculo con esa matricula: " + vehicle.getMatricula());
            }
            var userThird = userThirdJpaRepository.findById(vehicle.getUserThirdId()).orElseThrow(() -> new ResourceNotFoundException("Usuario de terceros no encontrado para el id " + vehicle.getUserThirdId()));

            vehicleThirdJpaRepository.save(vehicleThirdEntity);
        } else { // actualización
            VehicleThirdEntity existingVehicle = vehicleThirdJpaRepository.findById(vehicle.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado con id: " + vehicle.getId()));
            vehicleThirdMapper.updateVehicleThirdFromExisting(existingVehicle, vehicleThirdEntity);
            existingVehicle.setUserThird(existingVehicle.getUserThird());
            vehicleThirdJpaRepository.save(existingVehicle);
        }
    }

    @Override
    public Optional<VehicleThird> findById(Long id) {
        try {
            return vehicleThirdJpaRepository.findById(id)
                    .map(vehicleThirdMapper::fromEntityToDomain);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al buscar el vehículo con id: " + id);
        }
    }

    @Override
    public List<VehicleThird> findAll() {
        return vehicleThirdJpaRepository.findAll().stream()
                .map(vehicleThirdMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        try {
            vehicleThirdJpaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("No se encontró el vehiculo con id: " + id + " para eliminar.");
        }
    }

    @Override
    public Optional<VehicleThird> findByMatricula(String matricula) {
        try {
            return vehicleThirdJpaRepository.findByMatricula(matricula)
                    .map(vehicleThirdMapper::fromEntityToDomain);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al buscar el vehículo con matrícula: " + matricula);
        }
    }
}
