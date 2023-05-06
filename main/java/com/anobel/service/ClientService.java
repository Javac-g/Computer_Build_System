package com.anobel.service;

import com.anobel.model.Client;

import java.util.List;

public interface ClientService {
    Client create(Client client);
     Client readById(long id);
     Client update(Long id,Client client);
     void delete(long id);
	 Client readByLogin(String login);

    List<Client> getAllClients();
}
