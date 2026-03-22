package com.api.biblioteca.exception;

public class ExceptionTitleExists extends RuntimeException {
    public ExceptionTitleExists() {
        super("Já existe um livro com este título ");
    }
    
}
