package io.github.danilotomassoni.libraryapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.danilotomassoni.libraryapi.model.Book;

public interface BookRepository extends JpaRepository<Book, UUID>{

}
