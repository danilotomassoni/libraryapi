package io.github.danilotomassoni.libraryapi.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.danilotomassoni.libraryapi.dtos.ResponseError;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> listErrors = e.getFieldErrors();
        List<io.github.danilotomassoni.libraryapi.dtos.FieldError> responseErrors = listErrors
            .stream()
            .map(f -> new io.github.danilotomassoni.libraryapi.dtos.FieldError(f.getField(),f.getDefaultMessage()))
            .collect(Collectors.toList());

        return new ResponseError(HttpStatus.UNPROCESSABLE_CONTENT.value(),"VALIDATION ERROR",responseErrors);
    }
}
