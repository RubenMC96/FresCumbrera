package com.rmc.app.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e) {
        // Aquí puedes personalizar el mensaje y el estado HTTP que deseas devolver
        return new ResponseEntity<>("No tiene permisos para acceder a este recurso", HttpStatus.FORBIDDEN);
    }
}
