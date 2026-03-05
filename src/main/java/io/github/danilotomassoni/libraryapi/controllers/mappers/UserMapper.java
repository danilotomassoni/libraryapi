package io.github.danilotomassoni.libraryapi.controllers.mappers;

import org.mapstruct.Mapper;

import io.github.danilotomassoni.libraryapi.controllers.dtos.UserDTO;
import io.github.danilotomassoni.libraryapi.model.User;

@Mapper(componentModel= "spring")
public interface UserMapper {

    User toEntity(UserDTO dto);

}
