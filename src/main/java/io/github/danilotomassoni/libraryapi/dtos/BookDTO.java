package io.github.danilotomassoni.libraryapi.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.UUID;

import io.github.danilotomassoni.libraryapi.model.GenderType;

public record BookDTO(
    String isbn,
    String title,
    LocalDate publicationDate,
    GenderType gender,
    BigDecimal price,
    UUID idAuthor
){}
