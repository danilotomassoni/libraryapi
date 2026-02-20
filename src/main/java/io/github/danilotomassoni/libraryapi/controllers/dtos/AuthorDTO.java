package io.github.danilotomassoni.libraryapi.controllers.dtos;

import java.time.LocalDate;
import java.util.UUID;

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
    ) {}
