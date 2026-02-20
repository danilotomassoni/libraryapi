package io.github.danilotomassoni.libraryapi.controllers.dtos;

import java.util.List;

import org.springframework.http.HttpStatus;

public record ResponseError(int status, String message, List<FieldError> errors) {

    public static ResponseError responseError(String message) {
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static ResponseError conflictError(String message) {
        return new ResponseError(HttpStatus.CONFLICT.value(), message, List.of());
    }
}
