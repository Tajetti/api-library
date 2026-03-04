package com.api.biblioteca.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.biblioteca.model.dto.ClientRequestDTO;
import com.api.biblioteca.model.entity.ClientEntity;
import com.api.biblioteca.model.repository.ClientRepository;

import jakarta.transaction.Transactional;

@Service
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ClientEntity create(ClientRequestDTO dto) {
        String email = dto.getEmail() == null ? null : dto.getEmail().trim().toLowerCase();
        String name  = dto.getName()  == null ? null : dto.getName().trim();
        String phone = dto.getPhone() == null ? null : dto.getPhone().trim();

        if (repository.existsByEmailIgnoreCase(email)) { 
            throw new IllegalArgumentException("email já cadastrado");
        }

        ClientEntity client = new ClientEntity();
        client.setName(name);
        client.setPhone(phone);
        client.setEmail(email);
        client.setPassword(passwordEncoder.encode(dto.getPassword()));

        return repository.save(client);
    }

}
