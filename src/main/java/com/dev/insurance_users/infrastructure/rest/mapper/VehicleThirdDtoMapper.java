package com.dev.insurance_users.infrastructure.rest.mapper;

import com.dev.insurance_users.application.domain.VehicleThird;
import com.dev.insurance_users.generated.model.ThirdPartyVehicleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleThirdDtoMapper {

    VehicleThird fromDtoToDomain(ThirdPartyVehicleDto vehicleThirdDto);

    ThirdPartyVehicleDto fromDomainToDto(VehicleThird vehicleThird);
}
