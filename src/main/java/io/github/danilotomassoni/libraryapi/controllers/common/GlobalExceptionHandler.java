package io.github.danilotomassoni.libraryapi.controllers.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.danilotomassoni.libraryapi.controllers.dtos.ResponseError;
import io.github.danilotomassoni.libraryapi.exceptions.OperationNotPermittedException;
import io.github.danilotomassoni.libraryapi.exceptions.RegisterDuplicateException;
import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> listErrors = e.getFieldErrors();
        List<io.github.danilotomassoni.libraryapi.controllers.dtos.FieldError> responseErrors = listErrors
            .stream()
            .map(f -> new io.github.danilotomassoni.libraryapi.controllers.dtos.FieldError(f.getField(),f.getDefaultMessage()))
            .collect(Collectors.toList());

        return new ResponseError(HttpStatus.UNPROCESSABLE_CONTENT.value(),"VALIDATION ERROR",responseErrors);
    }

    @ExceptionHandler(RegisterDuplicateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleRegisterDuplicateException(RegisterDuplicateException e){
        return ResponseError.conflictError(e.getMessage());
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleOperationNotPermittedException(OperationNotPermittedException e){
        return ResponseError.responseError(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handleErrors(RuntimeException e){
        return  new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),List.of());
    }

   
}
