package com.ivan.logisticsapi.service;

import com.ivan.logisticsapi.dto.ClientRequest;
import com.ivan.logisticsapi.dto.ClientResponse;
import com.ivan.logisticsapi.model.Client;
import com.ivan.logisticsapi.repository.ClientRepository;
import com.ivan.logisticsapi.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ShipmentRepository shipmentRepository;

    public ClientService(ClientRepository clientRepository, ShipmentRepository shipmentRepository) {
        this.clientRepository = clientRepository;
        this.shipmentRepository = shipmentRepository;
    }
    public List<ClientResponse> getClients(){
        return clientRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }
    public ClientResponse addClient(ClientRequest clientRequest){
        Client client = new Client(
                clientRequest.getName(),
                clientRequest.getEmail(),
                clientRequest.getPhone(),
                clientRequest.getAddress());
        Client savedClient = clientRepository.save(client);


        return toResponse(savedClient);
    }
    private ClientResponse toResponse(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getPhone(),
                client.getAddress()
        );
    }
    public ClientResponse findClientById(Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Client with id " + id + " not found"));

        return toResponse(client);
    }
    public void deleteClientById(Long id){
        if(shipmentRepository.existsByClientId(id)){
            throw new IllegalStateException("Cannot delete client with id " + id + " because it has related shipments");
        }
        findClientById(id);
        clientRepository.deleteById(id);
    }
    public ClientResponse findClientByEmail(String email){
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(()-> new NoSuchElementException("Client with email: " + email + " not found"));

        return toResponse(client);
    }
    private Client getClientEntityById(Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Client with id " + id + " not found"));
        return client;
    }
    public ClientResponse updateClientById(Long id, ClientRequest clientRequest){
        Client client = getClientEntityById(id);
        client.setName(clientRequest.getName());
        client.setAddress(clientRequest.getAddress());
        client.setEmail(clientRequest.getEmail());
        client.setPhone(clientRequest.getPhone());
        Client savedClient = clientRepository.save(client);
        return toResponse(savedClient);
    }
}
