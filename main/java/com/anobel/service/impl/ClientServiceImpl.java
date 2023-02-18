package com.anobel.service.impl;
import com.anobel.model.Role;
import com.anobel.exception.NullEntityReferenceException;
import com.anobel.model.Client;
import com.anobel.repository.ClientRepository;
import com.anobel.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "ClientServiceImpl")
public class ClientServiceImpl implements ClientService {
    @Autowired
    private  ClientRepository clientRepository;


    @Override
    public List<Client> getAllClients() {

        return clientRepository.findAll();

    }

    @Override
    public Client create(Client client) {
        if (client != null) {
			Role user = new Role();
			user.setId(1L);
			client.setRole(user);
            return clientRepository.save(client);
        }
        throw new NullEntityReferenceException("Client cannot be null");
    }

    @Override
    public Client readById(long id) {
        return clientRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Client with id: " + id + " not found")
        );
    }

    @Override
    public Client update(long id,Client client) {
        if (client != null){
            Client client1 = readById(id);
            client1.setFirstName(client.getFirstName());
            client1.setSecondName(client.getSecondName());
            client1.setDiscount(client.getDiscount());
            client1.setEmail(client.getEmail());
            client1.setPassword(client.getPassword());
            client1.setOrders(client.getOrders());
            client1.setRole(client.getRole());
            client1.setId(client.getId());
            return clientRepository.save(client1);
        }
         throw new NullEntityReferenceException("Client cannot be null");
    }

    @Override
    public void delete(long id) {
        Client clientToDelete = readById(id);
        clientRepository.delete(clientToDelete);
    }
}
