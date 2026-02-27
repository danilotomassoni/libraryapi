package io.github.danilotomassoni.libraryapi.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.danilotomassoni.libraryapi.exceptions.InvalidFieldException;
import io.github.danilotomassoni.libraryapi.exceptions.RegisterDuplicateException;
import io.github.danilotomassoni.libraryapi.model.Book;
import io.github.danilotomassoni.libraryapi.repositories.BookRepository;


@Component
public class BookValidation {
    
    private static final int YEAR_REQUIREMENT = 2020;

    @Autowired    
    private BookRepository repository;

    public void validation(Book book){
        if(existsBookIsbn(book)){
            throw new RegisterDuplicateException("ISBN ja registered!");
        }

        if(isPriceNull(book)){
            throw new InvalidFieldException("price","Price starting in 2020 is mandatory!");
        }
    }

    private boolean existsBookIsbn(Book book){
        Optional<Book> bookOptional = repository.findByIsbn(book.getIsbn());
        if(book.getId() == null){
            return bookOptional.isPresent();
        }
        
        return bookOptional
        .map(Book::getId)
        .stream()
        .anyMatch(id -> id.equals(book.getId()));

    }

    private boolean isPriceNull(Book book){
        return book.getPrice() == null && book.getPublicationDate().getYear() >= YEAR_REQUIREMENT;
    }
}
