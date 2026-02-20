package io.github.danilotomassoni.libraryapi.controllers.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.validator.constraints.ISBN;

import io.github.danilotomassoni.libraryapi.model.GenderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record BookDTO(
    @ISBN
    @NotBlank(message="Required field")
    String isbn,
    @NotBlank(message="Required field")
    String title,
    @NotNull(message="Required field")
    @Past(message="It can't be a future date!")
    LocalDate publicationDate,
    GenderType gender,
    BigDecimal price,
    @NotNull(message="Required field")
    UUID idAuthor
){}
