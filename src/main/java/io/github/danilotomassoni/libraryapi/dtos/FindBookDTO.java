package io.github.danilotomassoni.libraryapi.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import io.github.danilotomassoni.libraryapi.model.GenderType;

public record FindBookDTO(
    UUID id,
    String isbn,
    String title,
    LocalDate publicationDate,
    GenderType gender,
    BigDecimal price,
    AuthorDTO author
){

}
