package io.github.danilotomassoni.libraryapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.danilotomassoni.libraryapi.controllers.dtos.UserDTO;
import io.github.danilotomassoni.libraryapi.controllers.mappers.UserMapper;
import io.github.danilotomassoni.libraryapi.services.UserService;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserDTO dto) {
        var user = mapper.toEntity(dto);
        service.save(user);
    }
    

}
