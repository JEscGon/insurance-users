package com.dev.insurance_users.infrastructure.repository.mapper;

import com.dev.insurance_users.application.domain.Vehicle;
import com.dev.insurance_users.infrastructure.entity.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VehicleMapper {

    Vehicle fromEntityToDomain(VehicleEntity vehicleEntity);

    VehicleEntity fromDomainToEntity(Vehicle vehicle);

    void updateVehicleFromExisting(@MappingTarget Vehicle target, Vehicle source);
    void updateVehicleFromExisting(@MappingTarget VehicleEntity target, VehicleEntity source);

}
