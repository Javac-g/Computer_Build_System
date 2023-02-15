package com.anobel.service.impl;

import com.anobel.model.Client;
import com.anobel.repository.ClientRepository;
import com.anobel.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "ClientServiceImpl")
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        super();
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {

        return clientRepository.findAll();

    }
}
