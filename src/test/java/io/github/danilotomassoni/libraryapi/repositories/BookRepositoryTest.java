package io.github.danilotomassoni.libraryapi.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.danilotomassoni.libraryapi.model.Author;
import io.github.danilotomassoni.libraryapi.model.Book;
import io.github.danilotomassoni.libraryapi.model.GenderType;

@SpringBootTest
public class BookRepositoryTest {
    
    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    //@Test
    public void saveBookTest(){
        Book book = new Book();
        book.setIsbn("90888-88772");
        book.setPrice(BigDecimal.valueOf(500));
        book.setGender(GenderType.DOCUMENTARY);
        book.setTitle("US ARMY");
        book.setPublicationDate(LocalDate.of(2000, 12, 10));
        
        Author author = authorRepository.findById(UUID.fromString("2cb8903a-e45b-444d-867a-7d4e5f3149b5")).orElse(null);

        book.setAuthor(author);

        repository.save(book);
    }

    //@Test
    public void saveBookCascadeTest(){
        Book book = new Book();
        book.setIsbn("90888-88773");
        book.setPrice(BigDecimal.valueOf(100));
        book.setGender(GenderType.FANTASY);
        book.setTitle("NARNIA OF EVENT");
        book.setPublicationDate(LocalDate.of(2010, 10, 20));
        
        //Author author = authorRepository.findById(UUID.fromString("2cb8903a-e45b-444d-867a-7d4e5f3149b5")).orElse(null);

        Author author = new Author();
        author.setName("Renato");
        author.setNationality("Brasileira");
        author.setDateBirth(LocalDate.of(1999, 8, 25));

        book.setAuthor(author);

        repository.save(book);
    }

    //@Test
    public void saveBookAndAuthorTest(){
        Book book = new Book();
        book.setIsbn("90888-88774");
        book.setPrice(BigDecimal.valueOf(1500));
        book.setGender(GenderType.WAR);
        book.setTitle("NARNIA OF EVENT WAR");
        book.setPublicationDate(LocalDate.of(2025, 10, 20));
        
        Author author = new Author();
        author.setName("Rubio");
        author.setNationality("Extrangeira");
        author.setDateBirth(LocalDate.of(1979, 8, 25));

        authorRepository.save(author);

        book.setAuthor(author);

        repository.save(book);
    }

    //@Test
    public void updateBookTest(){

        UUID bookId = UUID.fromString("1c5b9480-437d-46af-b781-6f092f37e5d8");
        var  book = repository.findById(bookId).orElse(null);

        UUID authorId = UUID.fromString("245bba0c-1911-4650-bc77-b2bdb3fda6e2");
        Author author = authorRepository.findById(authorId).orElse(null);

        book.setAuthor(author);

        repository.save(book);
    }

    //@Test
    public void deleteByIdTest(){
        repository.deleteById(UUID.fromString("3290c11f-c5bd-4eb6-a64a-42efae8bb4b7"));
    }

    @Test
    //@Transactional
    public void findBookTest(){
        UUID id = UUID.fromString("676f9f98-b13e-4c3d-9b01-56d9a4f97305");
        Book book = repository.findById(id).orElse(null);
        System.out.println("\n");
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor().getName());
        System.out.println("\n");
    }

    @Test
    public void findByTitle(){
        List<Book> books = repository.findByTitle("GOLD MASTER NEMO 2");
        books.forEach(System.out::println);
    }

    @Test
    public void findByIsbn(){
        List<Book> books = repository.findByIsbn("90888-88773");
        books.forEach(System.out::println);
    }

    @Test
    public void findAll(){
        var books = repository.findAll();
        books.forEach(System.out::println);
    }
    @Test
    public void findAuthorAll(){
        var authors = repository.findAuthorAll();
        authors.forEach(System.out::println);
    }

    @Test
    public void findByGenderQueryParam(){
        var books = repository.findByGender(GenderType.FANTASY, "price");
        books.forEach(System.out::println);
    }

    @Test
    public void findByGenderPositionalParam(){
        var books = repository.findByGender(GenderType.FANTASY, "price");
        books.forEach(System.out::println);
    }

}
