package io.github.danilotomassoni.libraryapi.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.danilotomassoni.libraryapi.model.Author;

public interface AuthorRepository extends JpaRepository<Author, UUID>{
    
    List<Author> findByName(String name);
    List<Author> findByNationality(String nationality);
    List<Author> findByNameAndNationality(String name, String nationality); 

}
