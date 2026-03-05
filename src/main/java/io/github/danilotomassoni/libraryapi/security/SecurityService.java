package io.github.danilotomassoni.libraryapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.github.danilotomassoni.libraryapi.model.User;
import io.github.danilotomassoni.libraryapi.services.UserService;

@Component
public class SecurityService {

    @Autowired
    private UserService service;

    public User userLogged(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails =(UserDetails) auth.getPrincipal();

        return service.getLoad(userDetails.getUsername());
    }

}
