package io.github.danilotomassoni.libraryapi.controllers.dtos;

import java.util.List;

public record UserDTO(String login, String password, List<String> roles) {
}
