package com.ivan.logisticsapi.controller;

import com.ivan.logisticsapi.dto.ClientRequest;
import com.ivan.logisticsapi.dto.ClientResponse;
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
    public ClientResponse addClient(@Valid @RequestBody ClientRequest client){
       return clientService.addClient(client);
    }
    @GetMapping
    public List<ClientResponse> getClients(){
        return clientService.getClients();
    }
    @GetMapping("/{id}")
    public ClientResponse getClientById(@PathVariable Long id){
        return clientService.findClientById(id);
    }

    @PutMapping("/{id}")
    public ClientResponse updateClient(
            @PathVariable Long id,
            @Valid @RequestBody ClientRequest client){
        return clientService.updateClientById(id,client);
    }
    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable Long id){
        clientService.deleteClientById(id);
    }
    @GetMapping("/email/{email}")
    public ClientResponse getClientByEmail(@PathVariable String email){
        return clientService.findClientByEmail(email);
    }

}
