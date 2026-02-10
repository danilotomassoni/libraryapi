package io.github.danilotomassoni.libraryapi.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.danilotomassoni.libraryapi.model.Author;
import io.github.danilotomassoni.libraryapi.model.Book;
import io.github.danilotomassoni.libraryapi.model.GenderType;
import io.github.danilotomassoni.libraryapi.repositories.AuthorRepository;
import io.github.danilotomassoni.libraryapi.repositories.BookRepository;

/**
 * @see TransactionServiceTest
 */
@Service
public class TransactionService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    
    @Transactional
    public void saveBookAndAuthor(){
        
        Author author = new Author();
        author.setName("Maria");
        author.setNationality("BRASILEIRA");
        author.setDateBirth(LocalDate.of(1979, 8, 25));

        authorRepository.save(author);

        Book book = new Book();
        book.setIsbn("99999-88881");
        book.setPrice(BigDecimal.valueOf(1500));
        book.setGender(GenderType.DOCUMENTARY);
        book.setTitle("2 WAR WORLD");
        book.setPublicationDate(LocalDate.of(1999, 10, 20));
        
        book.setAuthor(author);

        bookRepository.save(book);

        if(author.getName().equals("Maria")){
            throw new RuntimeException("Looback!");
        }
    }

    @Transactional
    public void updateWithoutUpdating(){
        var book = bookRepository.findById(UUID.fromString("3e0bafac-7153-42c6-bc22-634d91396d4f")).orElse(null);

        book.setPublicationDate(LocalDate.of(2026, 1, 1));
    }


}
