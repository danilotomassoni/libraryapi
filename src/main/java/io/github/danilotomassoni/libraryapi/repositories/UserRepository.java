package io.github.danilotomassoni.libraryapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.danilotomassoni.libraryapi.model.User;


public interface  UserRepository extends JpaRepository<User, UUID>{

    User findByLogin(String login);

}
