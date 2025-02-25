package com.dev.insurance_users.infrastructure.rest.controller.mapper;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.generated.model.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    User fromDtoToDomain(UserDto userDto);
    UserDto fromDomainToDto(User userDomain);
}
