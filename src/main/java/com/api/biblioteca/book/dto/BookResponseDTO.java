package com.api.biblioteca.book.dto;

import lombok.Data;

@Data
public class BookResponseDTO {
    private String id;
    private String title;
    private String author;
    private Integer year;
    private Boolean available;    
}
