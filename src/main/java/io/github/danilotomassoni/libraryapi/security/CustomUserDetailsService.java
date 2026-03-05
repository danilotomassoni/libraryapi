package io.github.danilotomassoni.libraryapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.github.danilotomassoni.libraryapi.model.User;
import io.github.danilotomassoni.libraryapi.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{


    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = service.getLoad(login);

        if(user == null){
            throw new UsernameNotFoundException("User not found.");
        }

        return org.springframework.security.core.userdetails.User.builder()
        .username(user.getLogin())
        .password(user.getPassword())
        .roles(user.getRoles().toArray(new String[user.getRoles().size()]))
        .build();
    }

}
