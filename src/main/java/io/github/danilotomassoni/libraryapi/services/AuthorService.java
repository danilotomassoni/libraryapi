package io.github.danilotomassoni.libraryapi.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import io.github.danilotomassoni.libraryapi.exceptions.OperationNotPermittedException;
import io.github.danilotomassoni.libraryapi.model.Author;
import io.github.danilotomassoni.libraryapi.repositories.AuthorRepository;
import io.github.danilotomassoni.libraryapi.repositories.BookRepository;
import io.github.danilotomassoni.libraryapi.validation.AuthorValidation;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorValidation validation;

    @Autowired
    private BookRepository bookRepository;

    public Author save(Author author){
        validation.validation(author);
        return repository.save(author);
    }

    public Optional<Author> findById(String id){
        return repository.findById(UUID.fromString(id));
    }

    public void delete(Author author){
        if(isPresentBook(author)){
            throw new OperationNotPermittedException("The author has books registered!");
        }
        repository.deleteById(UUID.fromString(author.getId().toString()));
    }

    public List<Author> find(String name,String nationality){
        if(name != null && nationality != null){
            return repository.findByNameAndNationality(name, nationality);
        }

        if(name != null){
            return repository.findByName(name);
        }

        if(nationality != null){
            return repository.findByNationality(nationality);
        }

        return repository.findAll();
    }

    public void update(Author author){
        if(author.getId() == null){
            throw  new IllegalArgumentException("Erro updated! [ "+LocalDateTime.now()+" ]");
        }

        validation.validation(author);
        repository.save(author);
    }

    public List<Author> findByExample(String name, String nationality){
        Author author = new Author();
        author.setName(name);
        author.setNationality(nationality);

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Author> authorExample = Example.of(author, matcher);
        
        return repository.findAll(authorExample);

    }

    public boolean isPresentBook(Author author){
        return bookRepository.existsByAuthor(author);
    }

}
