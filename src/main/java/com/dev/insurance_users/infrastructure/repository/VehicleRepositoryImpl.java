package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.Vehicle;
import com.dev.insurance_users.application.repository.VehicleRepository;

import com.dev.insurance_users.infrastructure.entity.UserEntity;
import com.dev.insurance_users.infrastructure.entity.VehicleEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserJpaRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.VehicleJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.VehicleMapper;
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
    private final UserJpaRepository userJpaRepository;

    @Override
    public void save(Vehicle vehicle) {
        VehicleEntity vehicleEntity = vehicleMapper.fromDomainToEntity(vehicle);
        Optional<UserEntity> userEntity = userJpaRepository.findById(Long.valueOf(vehicle.getUserId()));

        if(vehicle.getId() == null){
            vehicleEntity.setDateOfRegistration(LocalDate.now()); //TODO: orElseThrow
            if(userEntity.isPresent()){
                vehicleEntity.setUser(userEntity.get());
            } else {
                throw new RuntimeException("User not found");
            }
            vehicleJpaRepository.save(vehicleEntity);
        } else {
            var existingVehicleOpt = vehicleJpaRepository.findById(vehicle.getId()); //TODO: orElseThrow

            if(existingVehicleOpt.isPresent()){
                var existingVehicle = existingVehicleOpt.get();
                vehicle.setDateOfRegistration(existingVehicle.getDateOfRegistration());
                vehicle.setDateOfLastUpdate(LocalDate.now());

                VehicleEntity updatedEntity = vehicleMapper.fromDomainToEntity(vehicle);
                updatedEntity.setUser(existingVehicle.getUser());

                vehicleJpaRepository.save(updatedEntity);
                //TODO : NO USAR RUNTIME EXCEPTION usar excepciones personalizadas.
            } else {
                throw new RuntimeException("Vehicle not found");
            }
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

}
