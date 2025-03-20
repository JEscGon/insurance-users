package com.dev.insurance_users.infrastructure.repository.mapper;

import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.infrastructure.entity.UserEntity;
import com.dev.insurance_users.infrastructure.entity.VehicleThirdEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VehicleThirdMapper {
    @Mapping(source = "userThird.id", target = "userThirdId")
    VehicleThird fromEntityToDomain(VehicleThirdEntity vehicleEntity);

    VehicleThirdEntity fromDomainToEntity(VehicleThird vehicle);

    void updateVehicleThirdFromExisting(@MappingTarget VehicleThirdEntity target, VehicleThirdEntity source);



}
