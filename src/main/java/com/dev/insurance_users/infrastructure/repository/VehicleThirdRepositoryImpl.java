package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.UserThird;
import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.application.repository.VehicleThirdRepository;
import com.dev.insurance_users.infrastructure.entity.UserThirdEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.UserThirdJpaRepository;
import com.dev.insurance_users.infrastructure.repository.jpa.VehicleThirdJpaRepository;
import com.dev.insurance_users.infrastructure.repository.mapper.VehicleThirdMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
    var vehicleThirdEntity = vehicleThirdMapper.fromDomainToEntity(vehicle);
    Optional<UserThirdEntity> userThirdEntity = userThirdJpaRepository.findById(Long.valueOf(vehicle.getUserThirdId()));
        if(vehicle.getId() == null){
            vehicleThirdEntity.setDateOfRegistration(LocalDate.now());
            if(userThirdEntity.isPresent()){
                vehicleThirdEntity.setUserThird(userThirdEntity.get());
            } else {
                throw new RuntimeException("User not found");
            }
            vehicleThirdJpaRepository.save(vehicleThirdEntity);
        } else {
            var existingVehicleOpt = vehicleThirdJpaRepository.findById(vehicle.getId());

            if(existingVehicleOpt.isPresent()){
                var existingVehicle = existingVehicleOpt.get();
                vehicle.setDateOfRegistration(existingVehicle.getDateOfRegistration());
                vehicle.setDateOfLastUpdate(LocalDate.now());

                var updatedEntity = vehicleThirdMapper.fromDomainToEntity(vehicle);
                updatedEntity.setUserThird(existingVehicle.getUserThird());

                vehicleThirdJpaRepository.save(updatedEntity);
            } else {
                throw new RuntimeException("Vehicle not found");
            }
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
}
