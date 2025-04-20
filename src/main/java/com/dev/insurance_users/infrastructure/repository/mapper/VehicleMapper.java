package com.dev.insurance_users.infrastructure.repository.mapper;

import com.dev.insurance_users.application.domain.Vehicle;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.UserEntity;
import com.dev.insurance_users.infrastructure.repository.jpa.entity.VehicleEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,uses = {UserMapper.class})
public interface VehicleMapper {

    @Mapping(source = "user.id", target = "userId")
    Vehicle fromEntityToDomain(VehicleEntity vehicleEntity);

    VehicleEntity fromDomainToEntity(Vehicle vehicle);

    void updateVehicleFromExisting(@MappingTarget VehicleEntity target, VehicleEntity source);

    @Named("userEntityToLong")
    default Long userEntityToLong(UserEntity userEntity) {
        return userEntity != null ? userEntity.getId() : null;
    }
}


