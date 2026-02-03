package io.github.danilotomassoni.libraryapi.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.danilotomassoni.libraryapi.model.Author;
import io.github.danilotomassoni.libraryapi.model.Book;

/**
 * @see BookRepositoryTest
 */
public interface BookRepository extends JpaRepository<Book, UUID>{

    List<Book> findByAuthor(Author author);

    List<Book> findByTitle(String title);

    List<Book> findByIsbn(String isbn);

    //JPQL
    @Query("SELECT b FROM Book AS b ORDER BY b.title")
    List<Book> findAll();

    @Query("SELECT a FROM Book b JOIN b.author a")
    List<Author> findAuthorAll();

}
