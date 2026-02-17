package io.github.danilotomassoni.libraryapi.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.danilotomassoni.libraryapi.exceptions.RegisterDuplicateException;
import io.github.danilotomassoni.libraryapi.model.Author;
import io.github.danilotomassoni.libraryapi.repositories.AuthorRepository;

@Component
public class AuthorValidation {

    @Autowired
    private AuthorRepository repository;

    public void validation(Author author){
        if(isPresentAuthor(author)){
            throw new RegisterDuplicateException("Author registered!");   
        }

    }

    private boolean isPresentAuthor(Author author){
        Optional<Author> authorRegistered = repository.findByNameAndDateBirthAndNationality(
            author.getName(), 
            author.getDateBirth(), 
            author.getNationality()
        );
        if(author.getId() == null){
            return authorRegistered.isPresent();
        }

        return !author.getId().equals(authorRegistered.get().getId()) && authorRegistered.isPresent();
    }
}
