package com.dev.insurance_users.application.repository;

import com.dev.insurance_users.application.domain.Vehicle;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    public void save(Vehicle vehicle);
    public Optional<Vehicle> findById(Long id);
    public List<Vehicle> findAll();
    public void deleteById(Long id);
    public Optional<Vehicle> findByMatricula(String matricula);
    void deleteByUserId(Long userId);

}
