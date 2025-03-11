package com.dev.insurance_users.infrastructure.rest.controller.mapper;

import com.dev.insurance_users.application.domain.Vehicle;
import com.dev.insurance_users.generated.model.VehicleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleDtoMapper {

    VehicleDto fromDomainToDto(Vehicle vehicleDomain);

    Vehicle fromDtoToDomain(VehicleDto vehicleDto);

}
