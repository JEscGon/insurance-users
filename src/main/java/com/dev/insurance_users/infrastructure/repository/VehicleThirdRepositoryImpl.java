package com.dev.insurance_users.infrastructure.repository;

import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.application.repository.VehicleThirdRepository;
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

    @Override
    public void save(VehicleThird vehicle) {
        if(vehicle.getId() == null){
            vehicle.setDateOfRegistration(LocalDate.now());
        } else {
            var existingVehicle = vehicleThirdJpaRepository.findById(vehicle.getId());
            var aux = existingVehicle.get().getDateOfRegistration();
            vehicle.setDateOfRegistration(aux);
            if(existingVehicle.isPresent()){
                vehicleThirdMapper.updateVehicleThirdFromExisting(vehicleThirdMapper.fromDomainToEntity(vehicle), existingVehicle.get());
            }
            vehicle.setDateOfLastUpdate(LocalDate.now());
        }
        vehicleThirdJpaRepository.save(vehicleThirdMapper.fromDomainToEntity(vehicle));
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
