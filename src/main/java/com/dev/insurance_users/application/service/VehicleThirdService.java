package com.dev.insurance_users.application.service;


import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.application.exception.DuplicateResourceException;
import com.dev.insurance_users.application.exception.PartSaveErrorException;
import com.dev.insurance_users.application.exception.ResourceNotFoundException;
import com.dev.insurance_users.application.repository.VehicleThirdRepository;
import com.dev.insurance_users.infrastructure.exception.PartSaveErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleThirdService {

    private final VehicleThirdRepository vehicleThirdRepository;

    public void save(List<VehicleThird> vehiclesThird) {
        for (VehicleThird vehicleThird : vehiclesThird) {
            try {
                vehicleThirdRepository.save(vehicleThird);

            } catch (DuplicateResourceException e) {
                throw new PartSaveErrorException("Vehiculo ya existe: " + vehicleThird.getMatricula() + e.getMessage(), PartSaveErrorType.EXISTING_VEHICLE);
            } catch (ResourceNotFoundException e) {
                throw new PartSaveErrorException("Usuario terceros no existe: " + vehicleThird.getUserThirdId() + e.getMessage(), PartSaveErrorType.USER_THIRD_PARTY_NOT_FOUND);
            } catch (Exception e) {
                throw new PartSaveErrorException("Error saving vehicle: " + vehicleThird.getMatricula() + e.getMessage(), PartSaveErrorType.INTERNAL_SERVER_ERROR);
            }
        }
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

    public Optional<VehicleThird> findByMatriculaThird(String matricula) {
        return vehicleThirdRepository.findByMatricula(matricula);
    }

}
