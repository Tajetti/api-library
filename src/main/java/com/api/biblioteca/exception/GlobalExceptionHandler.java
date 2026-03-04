package com.api.biblioteca.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> msg = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(err -> err.getField(), err -> err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(new ErrorResponse(msg));
    }

    public record ErrorResponse(Map<String, String> error) {}
}
