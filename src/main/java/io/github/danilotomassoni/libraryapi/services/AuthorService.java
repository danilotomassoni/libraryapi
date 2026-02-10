package io.github.danilotomassoni.libraryapi.services;

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
}
