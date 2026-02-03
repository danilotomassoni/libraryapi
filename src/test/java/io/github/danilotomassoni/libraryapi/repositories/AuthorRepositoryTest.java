package io.github.danilotomassoni.libraryapi.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.danilotomassoni.libraryapi.model.Author;
import io.github.danilotomassoni.libraryapi.model.Book;
import io.github.danilotomassoni.libraryapi.model.GenderType;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private BookRepository bookRepository;
    
    //@Test
    public void saveAuthorTest(){
        Author author = new Author();
        author.setName("José");
        author.setNationality("Brasileira");
        author.setDateBirth(LocalDate.of(1970, 5, 2));

        repository.save(author);
    }

    //@Test
    public void updateAuthorTest(){
        UUID uuid = UUID.fromString("069fdef0-a68d-4d2a-981b-c71c2f899190");
        Optional<Author> optional =  repository.findById(uuid);

        if(optional.isPresent()){
            Author author = optional.get();
            author.setName("José Update");
            author.setNationality("Brasileira");
            author.setDateBirth(LocalDate.of(1950, 12, 20));

            repository.save(author);
        }
    }

    @Test
    //@Transactional
    public void findTest(){
        UUID id = UUID.fromString("245bba0c-1911-4650-bc77-b2bdb3fda6e2");
        var author = repository.findById(id).get();

        List<Book> books = bookRepository.findByAuthor(author);
        author.setBooks(books);
        author.getBooks().forEach(System.out::println);
        
    }
    
    //@Test
    public void deleteByIdTest(){
        repository.deleteById(UUID.fromString("f31e4e4e-75a4-42ff-82a2-60aece8ba43c"));
    }

    //@Test
    public void deleteTest(){
        var id = UUID.fromString("069fdef0-a68d-4d2a-981b-c71c2f899190");
        var author = repository.findById(id).get();
        repository.delete(author);
    }

   // @Test
    public void saveAuthorAndBookTest(){
        Author author = new Author();
        author.setName("Danilo");
        author.setNationality("Brasileira");
        author.setDateBirth(LocalDate.of(1999, 5, 20));

        Book book = new Book();
        book.setIsbn("90888-88775");
        book.setPrice(BigDecimal.valueOf(160));
        book.setGender(GenderType.FANTASY);
        book.setTitle("GOLD MASTER NEMO 2");
        book.setPublicationDate(LocalDate.of(2020, 11, 20));
        book.setAuthor(author);

        author.setBooks(new ArrayList<>());
        author.getBooks().add(book);

        repository.save(author);

        bookRepository.saveAll(author.getBooks());
    }
}
