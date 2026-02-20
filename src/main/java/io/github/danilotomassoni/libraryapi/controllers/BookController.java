package io.github.danilotomassoni.libraryapi.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.danilotomassoni.libraryapi.controllers.dtos.BookDTO;
import io.github.danilotomassoni.libraryapi.controllers.mappers.BookMapper;
import io.github.danilotomassoni.libraryapi.model.Book;
import io.github.danilotomassoni.libraryapi.services.BookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("books")
public class BookController implements GenericController {

    @Autowired
    private BookService service;

    @Autowired
    private BookMapper mapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BookDTO bookDTO) {
        Book book = mapper.toEntity(bookDTO);
        service.save(book);
        URI location = generateHeaderLocation(book.getId());
        return ResponseEntity.created(location).build();
    }

}
