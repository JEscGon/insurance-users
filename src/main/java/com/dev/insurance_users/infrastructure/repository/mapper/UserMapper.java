package com.dev.insurance_users.infrastructure.repository.mapper;

import com.dev.insurance_users.application.domain.User;
import com.dev.insurance_users.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User fromEntityToDomain(UserEntity userEntity);

    UserEntity fromDomainToEntity(User user);

    void updateUserFromExisting(@MappingTarget UserEntity target, UserEntity source);

}
