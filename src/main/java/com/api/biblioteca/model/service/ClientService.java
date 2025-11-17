package com.api.biblioteca.model.service;

import com.api.biblioteca.model.entity.Client;
import com.api.biblioteca.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public String createClient(Client client) {
        repository.save(client);
        return "Client created successfully";
    }

    public List<Client> findAllClients() {
        Iterable<Client> clients = repository.findAll();
        return (List<Client>) clients;
    }

    public Client findClientByEmail(String email) {
        return repository.findClientByEmailIgnoreCase(email);
    }

    public Client updateClient(Long id, Client newClient) {
        Client existingClient = repository.findClientById(id);

        if(existingClient == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }

        if(newClient.getEmail() != null){
            existingClient.setEmail(newClient.getEmail());
        }

        repository.save(existingClient);
        return existingClient;
    }

    public boolean deleteClient(Long id) {
        Client existingClient = repository.findClientById(id);
        if(existingClient != null){
            repository.delete(existingClient);
        }
        return true;
    }
}
