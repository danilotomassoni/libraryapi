package io.github.danilotomassoni.libraryapi.dtos;

import java.time.LocalDate;
import java.util.UUID;

import io.github.danilotomassoni.libraryapi.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record  AuthorDTO(
    UUID id,
    @NotBlank(message="Required field")
    @Size(min=2 ,max=100, message="Outside the permitted limit.") String name,
    @NotNull(message="Required field")
    @Past(message="It can't be a future date!") LocalDate dateBirth,
    @NotBlank(message="Required field") String nationality
    ) {

    public Author mappedByAuthor(){
        Author author = new Author();
        author.setName(name);
        author.setDateBirth(dateBirth);
        author.setNationality(nationality);
        return author;
    }

}
