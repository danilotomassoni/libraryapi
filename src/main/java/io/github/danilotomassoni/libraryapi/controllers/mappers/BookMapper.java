package io.github.danilotomassoni.libraryapi.controllers.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.danilotomassoni.libraryapi.controllers.dtos.BookDTO;
import io.github.danilotomassoni.libraryapi.controllers.dtos.ResponseBookDTO;
import io.github.danilotomassoni.libraryapi.model.Book;
import io.github.danilotomassoni.libraryapi.repositories.AuthorRepository;

@Mapper(componentModel="spring", uses={AuthorMapper.class})
public abstract class BookMapper {

    @Autowired
    AuthorRepository authorRepository;

    @Mapping(target = "author", expression="java( authorRepository.findById(bookDTO.idAuthor()).orElse(null) )")
    public abstract Book toEntity(BookDTO bookDTO);

    public abstract ResponseBookDTO toDTO(Book book);

}
