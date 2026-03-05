package com.api.biblioteca.client.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientRequestDTO {

    @NotBlank(message="O campo nome não pode ser vazio")
    private String name;
    
    @NotBlank(message="O campo telefone não pode ser vazio")
    @Size(min=10, max=15, message="O campo telefone deve conter entre 10 e 15 caracteres")
    private String phone;
    
    @NotBlank(message="O campo email não pode ser vazio")
    @Email(message="O campo email deve ser um endereço de email válido")
    private String email;
    
    @NotBlank(message="O campo senha não pode ser vazio")
    @Size(min=6, message="O campo senha deve conter no mínimo 6 caracteres")
    private String password;

}