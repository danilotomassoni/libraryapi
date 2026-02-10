package io.github.danilotomassoni.libraryapi.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.danilotomassoni.libraryapi.model.Author;
import io.github.danilotomassoni.libraryapi.repositories.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public Author save(Author author){
        return repository.save(author);
    }

    public Optional<Author> findById(String id){
        return repository.findById(UUID.fromString(id));
    }

    public void delete(String id){
        repository.deleteById(UUID.fromString(id));
    }
}
