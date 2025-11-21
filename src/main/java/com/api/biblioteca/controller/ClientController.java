package com.api.biblioteca.controller;

import com.api.biblioteca.model.entity.Book;
import com.api.biblioteca.model.entity.Client;
import com.api.biblioteca.model.repository.ClientRepository;
import com.api.biblioteca.model.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody Client client){
        clientService.createClient(client);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        clientService.findAllClients();
        return ResponseEntity.ok().body(clientService.findAllClients());
    }

    @GetMapping("/email")
    public Client getClientByEmail(@RequestParam String email) {
        return clientService.findClientByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente não encontrado com esse email."
                ));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") Long id,
                                        @RequestBody Client client) throws  Exception {
        try{
            Client existingClient = clientService.updateClient(id, client);
            return  ResponseEntity.ok(existingClient);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        try{
            clientService.deleteClient(id);
            return ResponseEntity.ok(true);
        } catch (Exception e){
            return  ResponseEntity.status(400).body("Error deleting book!" + e.getMessage());
        }
    }
}
