package io.github.danilotomassoni.libraryapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.danilotomassoni.libraryapi.model.Author;

public interface AuthorRepository extends JpaRepository<Author, UUID>{

}
