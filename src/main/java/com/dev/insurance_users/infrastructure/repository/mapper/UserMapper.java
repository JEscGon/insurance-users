package com.dev.insurance_users.infrastructure.repository.mapper;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.infrastructure.repository.jpa.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromEntityToDomain(UserEntity userEntity);

    UserEntity fromDomainToEntity(User userDomain);

}

