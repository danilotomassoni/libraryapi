package io.github.danilotomassoni.libraryapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.danilotomassoni.libraryapi.dtos.BookDTO;
import io.github.danilotomassoni.libraryapi.dtos.ResponseError;
import io.github.danilotomassoni.libraryapi.exceptions.RegisterDuplicateException;
import io.github.danilotomassoni.libraryapi.services.BookService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BookDTO bookDTO) {
        try {

            return ResponseEntity.ok(bookDTO);
        } catch (RegisterDuplicateException e) {
            var error = ResponseError.conflictError(e.getMessage());
            return  ResponseEntity.status(error.status()).body(error);
        }
    }
    
}
