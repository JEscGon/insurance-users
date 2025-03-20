package com.dev.insurance_users.infrastructure.repository.mapper;

import com.dev.insurance_users.application.domain.UserThird;
import com.dev.insurance_users.infrastructure.entity.UserThirdEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserThirdMapper {

    UserThird fromEntityToDomain(UserThirdEntity userThirdEntity);

    UserThirdEntity fromDomainToEntity(UserThird userThird);

    void updateUserThirdFromExisting(@MappingTarget UserThirdEntity target, UserThirdEntity source);


}
