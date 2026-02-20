package io.github.danilotomassoni.libraryapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.danilotomassoni.libraryapi.dtos.AuthorDTO;
import io.github.danilotomassoni.libraryapi.model.Author;

@Mapper(componentModel="spring")
public interface AuthorMapper {
    @Mapping(source="name",target="name")
    Author toEntity(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);
    
}
