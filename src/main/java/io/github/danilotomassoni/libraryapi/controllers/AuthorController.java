package io.github.danilotomassoni.libraryapi.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.danilotomassoni.libraryapi.dtos.AuthorDTO;
import io.github.danilotomassoni.libraryapi.dtos.ResponseError;
import io.github.danilotomassoni.libraryapi.exceptions.OperationNotPermittedException;
import io.github.danilotomassoni.libraryapi.exceptions.RegisterDuplicateException;
import io.github.danilotomassoni.libraryapi.model.Author;
import io.github.danilotomassoni.libraryapi.services.AuthorService;



@RestController
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AuthorDTO authorDTO){
        try{
            Author author = authorDTO.mappedByAuthor();
            service.save(author);

            URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(author.getId())
            .toUri();

            return ResponseEntity.created(location).build();
        }catch(RegisterDuplicateException e){
            var errorDTO = ResponseError.conflictError(e.getMessage()); 
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
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
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        try{
            Optional<Author> optional = service.findById(id);

            if(optional.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            service.delete(optional.get());

            return ResponseEntity.noContent().build();
        }catch(OperationNotPermittedException e){
            var error = ResponseError.responseError(e.getMessage());

            return ResponseEntity.status(error.status()).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>>  find(
        @RequestParam(value = "name",required=false) String name,
        @RequestParam(value = "nationality",required=false) String nationality
    ) {

        List<Author> result = service.find(name, nationality);
        List<AuthorDTO> list = result
        .stream()
        .map(author -> new AuthorDTO(
            author.getId(),
            author.getName(),
            author.getDateBirth(),
            author.getNationality()))
        .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id ,@RequestBody AuthorDTO authorDTO){
        try{
            Optional<Author> optional = service.findById(id);

            if(optional.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            
            var author = optional.get();
            author.setName(authorDTO.name());
            author.setDateBirth(authorDTO.dateBirth());
            author.setNationality(authorDTO.nationality());
            
            service.update(author);

            return ResponseEntity.noContent().build();
        }catch(RegisterDuplicateException e){
            var errorDTO = ResponseError.conflictError(e.getMessage()); 
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
    }
    
}
