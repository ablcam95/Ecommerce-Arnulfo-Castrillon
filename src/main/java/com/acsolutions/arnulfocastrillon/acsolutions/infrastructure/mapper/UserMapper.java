package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.mapper;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.User;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings (
            {
                    @Mapping(source = "id" , target = "id"),
                    @Mapping(source = "username" , target = "username"),
                    @Mapping(source = "firstnameName" , target = "firstnameName"),
                    @Mapping(source = "lastnameName" , target = "lastnameName"),
                    @Mapping(source = "email" , target = "email"),
                    @Mapping(source = "address" , target = "address"),
                    @Mapping(source = "cellphone" , target = "cellphone"),
                    @Mapping(source = "password" , target = "password"),
                    @Mapping(source = "activo" , target = "activo"),
                    @Mapping(source = "userType" , target = "userType"),
                    @Mapping(source = "dateCreated" , target = "dateCreated"),
                    @Mapping(source = "dateUpdated" , target = "dateUpdated"),
            }

    )

    User toUser (UserEntity userEntity);
    Iterable<User> toUsers (Iterable<UserEntity> userEntities);

    @InheritInverseConfiguration
    UserEntity toUserEntity (User user);
    Iterable<UserEntity> toUserEntities (Iterable<User> users);

}
