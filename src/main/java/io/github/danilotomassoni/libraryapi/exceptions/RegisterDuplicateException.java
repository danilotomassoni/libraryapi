package io.github.danilotomassoni.libraryapi.exceptions;

public class RegisterDuplicateException extends RuntimeException{

    public RegisterDuplicateException(String message) {
        super(message);
    }

}
