package io.github.danilotomassoni.libraryapi.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.danilotomassoni.libraryapi.model.Book;
import io.github.danilotomassoni.libraryapi.model.GenderType;
import io.github.danilotomassoni.libraryapi.repositories.BookRepository;
import io.github.danilotomassoni.libraryapi.repositories.specs.BookSpec;
import io.github.danilotomassoni.libraryapi.validation.BookValidation;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookValidation validator;

    public Book save(Book book) {
        validator.validation(book);
        return repository.save(book);
    }

    public Optional<Book> findById(UUID id) {
        return repository.findById(id);
    }

    public void delete(Book book) {
        repository.delete(book);
    }

    public Page<Book> findAll(
            String isbn,
            String title,
            String nameAuthor,
            GenderType gender,
            Integer publicationDate,
            Integer page,
            Integer size) {
        /**
         * Specification<Book> specs = Specification.where(BookSpec.isbnEqual(isbn))
         * .and(BookSpec.titleLike(title)
         * .and(BookSpec.genderEqual(gender)));
         */

        Specification<Book> specs = Specification.where((root, q, cb) -> cb.conjunction());

        if (isbn != null) {
            specs = specs.and(BookSpec.isbnEqual(isbn));
        }
        if (title != null) {
            specs = specs.and(BookSpec.titleLike(title));
        }

        if (gender != null) {
            specs = specs.and(BookSpec.genderEqual(gender));
        }

        if (publicationDate != null) {
            specs = specs.and(BookSpec.publicationDateEqual(publicationDate));
        }
        if (nameAuthor != null) {
            specs = specs.and(BookSpec.nameAuthorLike(nameAuthor));
        }

        Pageable pageable = PageRequest.of(page, size);

        return repository.findAll(specs, pageable);
    }

    public void update(Book book) {
        if (book.getId() == null) {
            throw new IllegalArgumentException("Error updated!");
        }

        validator.validation(book);
        repository.save(book);
    }

}
