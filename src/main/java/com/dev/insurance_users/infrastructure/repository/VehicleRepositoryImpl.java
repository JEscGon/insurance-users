package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.Vehicle;
import com.dev.insurance_users.application.repository.VehicleRepository;
import com.dev.insurance_users.infrastructure.mapper.VehicleMapper;
import com.dev.insurance_users.infrastructure.repository.jpa.VehicleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class VehicleRepositoryImpl implements VehicleRepository {

    private final VehicleMapper vehicleMapper;
    private final VehicleJpaRepository vehicleJpaRepository;

    @Override
    public void save(Vehicle vehicle) {
        if(vehicle.getId() == null){
            vehicle.setDateOfRegistration(LocalDate.now());
        } else {
            var aux = vehicleJpaRepository.findById(vehicle.getId());
            if(aux.isPresent()){
                Vehicle existingVehicle = vehicleMapper.fromEntityToDomain(aux.get());
                vehicleMapper.updateVehicleFromExisting(existingVehicle, vehicle);
            }
            vehicle.setDateOfLastUpdate(LocalDate.now());
        }
        vehicleJpaRepository.save(vehicleMapper.fromDomainToEntity(vehicle));
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



}
