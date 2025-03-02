package org.openapitools.mappers;

import org.openapitools.model.User;
import org.openapitools.model.UserRegistration;
import org.openapitools.model.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true) // Ignoramos el id porque se genera en el servicio
    @Mapping(target = "status", constant = "REGISTERED") // Asignamos un valor constante
    @Mapping(target = "password", expression = "java(encodePassword(userRegistration.getPassword()))")
    User toUser(UserRegistration userRegistration);

    @Mapping(target = "rol", expression = "java(user.getRol().toString())") // Convertimos el enum a String
    UserResponse toUserResponse(User user);

    default String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}