package io.github.danilotomassoni.libraryapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.danilotomassoni.libraryapi.model.Book;
import io.github.danilotomassoni.libraryapi.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public Book save(Book book){ return repository.save(book);}

    
}
