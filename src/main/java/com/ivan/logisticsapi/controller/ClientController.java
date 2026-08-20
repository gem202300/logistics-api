package com.ivan.logisticsapi.controller;

import com.ivan.logisticsapi.model.Client;
import com.ivan.logisticsapi.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client addClient(@Valid @RequestBody Client client){
       return clientService.addClient(client);
    }
    @GetMapping
    public List<Client> getClients(){
        return clientService.getClients();
    }
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id){
        return clientService.findClientById(id);
    }

    @PutMapping("/{id}")
    public Client  updateClient(
            @PathVariable Long id,
            @Valid @RequestBody Client client){
        Client tempClient = clientService.findClientById(id);

        tempClient.setName(client.getName());
        tempClient.setAddress(client.getAddress());
        tempClient.setEmail(client.getEmail());
        tempClient.setPhone(client.getPhone());

        return clientService.addClient(tempClient);
    }
    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable Long id){
        clientService.deleteClientById(id);
    }


}
