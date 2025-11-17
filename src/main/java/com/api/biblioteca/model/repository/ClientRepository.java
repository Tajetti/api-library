package com.api.biblioteca.model.repository;

import org.springframework.data.repository.CrudRepository;
import com.api.biblioteca.model.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    Client findClientByEmailIgnoreCase(String email);
    Client findClientById(Long id);
}
