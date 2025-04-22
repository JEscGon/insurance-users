package com.dev.insurance_users.infrastructure.rest.mapper;

import com.dev.insurance_users.application.domain.UserThird;
import com.dev.insurance_users.generated.model.UserThirdDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserThirdDtoMapper {

    UserThird fromDtoToDomain(UserThirdDto userThirdDto);
    UserThirdDto fromDomainToDto(UserThird userThird);

}
