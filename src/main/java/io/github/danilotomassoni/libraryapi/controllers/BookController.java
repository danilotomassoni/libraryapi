package io.github.danilotomassoni.libraryapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.danilotomassoni.libraryapi.controllers.dtos.BookDTO;
import io.github.danilotomassoni.libraryapi.controllers.dtos.ResponseError;
import io.github.danilotomassoni.libraryapi.controllers.mappers.BookMapper;
import io.github.danilotomassoni.libraryapi.exceptions.RegisterDuplicateException;
import io.github.danilotomassoni.libraryapi.model.Book;
import io.github.danilotomassoni.libraryapi.services.BookService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private BookMapper mapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BookDTO bookDTO) {
        try {
            Book book = mapper.toEntity(bookDTO);
            service.save(book);
            return ResponseEntity.ok(book);
        } catch (RegisterDuplicateException e) {
            var error = ResponseError.conflictError(e.getMessage());
            return  ResponseEntity.status(error.status()).body(error);
        }
    }
    
}
