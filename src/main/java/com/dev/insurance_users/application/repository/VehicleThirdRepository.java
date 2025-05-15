package com.dev.insurance_users.application.repository;

import com.dev.insurance_users.application.domain.VehicleThird;

import java.util.List;
import java.util.Optional;

public interface VehicleThirdRepository {

    public Integer save(VehicleThird vehicle);

    public Optional<VehicleThird> findById(Long id);

    public List<VehicleThird> findAll();

    public void deleteById(Long id);

    Optional<VehicleThird> findByMatricula(String matricula);

}
