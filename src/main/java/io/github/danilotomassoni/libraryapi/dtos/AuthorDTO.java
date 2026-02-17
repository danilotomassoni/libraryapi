package io.github.danilotomassoni.libraryapi.dtos;

import java.time.LocalDate;
import java.util.UUID;

import io.github.danilotomassoni.libraryapi.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record  AuthorDTO(UUID id,@NotBlank(message="Required field!") String name,@NotNull(message="Required field!") LocalDate dateBirth,@NotBlank(message="Required field!") String nationality) {

    public Author mappedByAuthor(){
        Author author = new Author();
        author.setName(name);
        author.setDateBirth(dateBirth);
        author.setNationality(nationality);
        return author;
    }

}
