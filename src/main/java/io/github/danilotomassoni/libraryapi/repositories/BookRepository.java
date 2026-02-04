package io.github.danilotomassoni.libraryapi.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.danilotomassoni.libraryapi.model.Author;
import io.github.danilotomassoni.libraryapi.model.Book;
import io.github.danilotomassoni.libraryapi.model.GenderType;

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

    @Query("SELECT b FROM Book b WHERE b.gender = :gender ORDER BY :order")
    List<Book> findByGender(@Param("gender") GenderType gender, @Param("order") String order);

    @Query("SELECT b FROM Book b WHERE b.gender = ?1 ORDER BY ?2")
    List<Book> findByGenderPositional(GenderType gender, String order);


}
