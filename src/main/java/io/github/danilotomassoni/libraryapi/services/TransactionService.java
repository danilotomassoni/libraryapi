package io.github.danilotomassoni.libraryapi.services;

import java.math.BigDecimal;
import java.time.LocalDate;

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
        author.setName("João");
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

        if(author.getName().equals("João")){
            throw new RuntimeException("Looback!");
        }
    }


}
