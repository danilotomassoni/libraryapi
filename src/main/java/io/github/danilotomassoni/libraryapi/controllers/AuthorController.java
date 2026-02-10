package io.github.danilotomassoni.libraryapi.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.danilotomassoni.libraryapi.dtos.AuthorDTO;
import io.github.danilotomassoni.libraryapi.model.Author;
import io.github.danilotomassoni.libraryapi.services.AuthorService;


@RestController
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AuthorDTO authorDTO){

        Author author = authorDTO.mappedByAuthor();
        service.save(author);

        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(author.getId())
        .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable("id") String id) {

        Optional<Author> optional = service.findById(id);

        if(optional.isPresent()){
            Author author = optional.get();
            AuthorDTO authorDTO = new AuthorDTO(author.getId(),author.getName(),author.getDateBirth(),author.getNationality());
            return ResponseEntity.ok(authorDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {

        Optional<Author> optional = service.findById(id);

        if(optional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.delete(optional.get().getId().toString());

        return ResponseEntity.noContent().build();
    }
    
}
