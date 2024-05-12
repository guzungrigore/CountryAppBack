package org.example.pw7.controller;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.example.pw7.dto.ErrorMessage;
import org.example.pw7.dto.ValidationExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorMessage> entityExistsException(EntityExistsException ex,
                                                                    HttpServletRequest request) {
        ErrorMessage error = getErrorMessage(ex.getMessage(), request);
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException ex,
                                                                    HttpServletRequest request) {
        ErrorMessage error = getErrorMessage(ex.getMessage(), request);
        return ResponseEntity.status(NOT_FOUND).body(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorMessage> usernameNotFoundException(UsernameNotFoundException ex,
                                                                HttpServletRequest request) {
        ErrorMessage error = getErrorMessage(ex.getMessage(), request);
        return ResponseEntity.status(NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationExceptionResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ValidationExceptionResponse> errors = ex.getBindingResult()
                .getAllErrors().stream()
                .map(error -> new ValidationExceptionResponse(((FieldError) error).getField(), error.getDefaultMessage()))
                .toList();
        return ResponseEntity.status(BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRuntimeExceptions(RuntimeException ex, HttpServletRequest request) {
        ErrorMessage error = getErrorMessage(ex.getMessage(), request);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(error);
    }


    private static ErrorMessage getErrorMessage(String exceptionMessage, HttpServletRequest request) {
        return new ErrorMessage(exceptionMessage, request.getRequestURI());
    }
}
