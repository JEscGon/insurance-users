package com.dev.insurance_users.infrastructure.rest.mapper;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.generated.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    User fromDtoToDomain(UserDto userDto);
    UserDto fromDomainToDto(User userDomain);

}
