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

import io.github.danilotomassoni.libraryapi.controllers.dtos.AuthorDTO;
import io.github.danilotomassoni.libraryapi.controllers.mappers.AuthorMapper;
import io.github.danilotomassoni.libraryapi.model.Author;
import io.github.danilotomassoni.libraryapi.services.AuthorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("authors")
public class AuthorController implements GenericController {

    @Autowired
    private AuthorService service;

    @Autowired
    private AuthorMapper mapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid AuthorDTO authorDTO) {

        Author author = mapper.toEntity(authorDTO);
        service.save(author);
        URI location = generateHeaderLocation(author.getId());

        return ResponseEntity.created(location).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable("id") String id) {

        return service.findById(id)
                .map(author -> {
                    AuthorDTO authorDTO = mapper.toDTO(author);
                    return ResponseEntity.ok(authorDTO);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {

        Optional<Author> optional = service.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.delete(optional.get());

        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> find(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nationality", required = false) String nationality) {

        List<Author> result = service.findByExample(name, nationality);
        List<AuthorDTO> list = result
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody AuthorDTO authorDTO) {

        Optional<Author> optional = service.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var author = optional.get();
        author.setName(authorDTO.name());
        author.setDateBirth(authorDTO.dateBirth());
        author.setNationality(authorDTO.nationality());

        service.update(author);

        return ResponseEntity.noContent().build();

    }

}
