package com.dev.insurance_users.application.service;


import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.application.repository.VehicleThirdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleThirdService {

    private final VehicleThirdRepository vehicleThirdRepository;

    public void save(VehicleThird vehicleThird) {
        vehicleThirdRepository.save(vehicleThird);
    }

    public Optional<VehicleThird> findById(Long id) {
        return vehicleThirdRepository.findById(id);
    }

    public List<VehicleThird> findAll() {
        return vehicleThirdRepository.findAll();
    }

    public void deleteVehicleById(Long id) {
        vehicleThirdRepository.deleteById(id);
    }

}
