package com.dev.insurance_users.infrastructure.repository.mapper;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromEntityToDomain(UserEntity userEntity);

    UserEntity fromDomainToEntity(User userDomain);

}

