package com.dev.insurance_users.infrastructure.rest.controller.mapper;

import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.generated.model.VehicleThirdDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleThirdDtoMapper {

    VehicleThird fromDtoToDomain(VehicleThirdDto vehicleThirdDto);

    VehicleThirdDto fromDomainToDto(VehicleThird vehicleThird);
}
