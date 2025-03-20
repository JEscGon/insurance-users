package com.dev.insurance_users.infrastructure.repository.mapper;

import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.infrastructure.entity.VehicleThirdEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VehicleThirdMapper {

    VehicleThird fromEntityToDomain(VehicleThirdEntity vehicleEntity);

    VehicleThirdEntity fromDomainToEntity(VehicleThird vehicle);

    void updateVehicleThirdFromExisting(@MappingTarget VehicleThirdEntity target, VehicleThirdEntity source);

}
