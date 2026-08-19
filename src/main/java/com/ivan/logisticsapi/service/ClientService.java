package com.ivan.logisticsapi.service;

import com.ivan.logisticsapi.model.Client;
import com.ivan.logisticsapi.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public List<Client> getClients(){
        return clientRepository.findAll();
    }
    public Client addClient(Client client){
        return clientRepository.save(client);
    }
    public Client findClientByID(Long id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElseThrow(NoSuchElementException::new);
    }
    public void deleteClientByID(Long id){
        Client client = findClientByID(id);
        clientRepository.delete(client);
    }
}
