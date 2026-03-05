package io.github.danilotomassoni.libraryapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.danilotomassoni.libraryapi.model.User;
import io.github.danilotomassoni.libraryapi.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public void save(User user){
        var password = user.getPassword();
        user.setPassword(encoder.encode(password));
        repository.save(user);
    }

    public User getLoad(String login){

        return  repository.findByLogin(login);
    }
}
