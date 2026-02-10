package io.github.danilotomassoni.libraryapi.dtos;

import java.time.LocalDate;
import java.util.UUID;

import io.github.danilotomassoni.libraryapi.model.Author;

public record  AuthorDTO(UUID id,String name, LocalDate dateBirth, String nationality) {

    public Author mappedByAuthor(){
        Author author = new Author();
        author.setName(name);
        author.setDateBirth(dateBirth);
        author.setNationality(nationality);
        return author;
    }

}
