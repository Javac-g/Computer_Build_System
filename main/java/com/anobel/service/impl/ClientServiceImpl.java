package com.anobel.service.impl;

import com.anobel.exception.NullEntityReferenceException;
import com.anobel.model.Client;
import com.anobel.model.Role;
import com.anobel.repository.ClientRepository;
import com.anobel.repository.RoleRepository;
import com.anobel.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service(value = "ClientServiceImpl")
public class ClientServiceImpl implements ClientService {
    @Autowired
    private  ClientRepository clientRepository;

    @Autowired
    private RoleRepository roleRepository ;
	@Autowired
	private  PasswordEncoder passwordEncoder;

    @Override
    public List<Client> getAllClients() {

        return clientRepository.findAll();

    }

    @Override
    public Client create(Client client) {
        if (client != null) {
            Role user = roleRepository.findById(1L).get();

            client.setRole(user);
			client.setPassword(passwordEncoder.encode(client.getPassword()));
            return clientRepository.save(client);
        }
        throw new NullEntityReferenceException("Client cannot be null");
    }

    @Override
    public Client readById(long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public Client update(Long id,Client client) {
		Client client1 = readById(id);
		
        if (client1 != null){
             
			client1.setId(id);
            client1.setFullName(client.getFullName());
            client1.setLogin(client.getLogin());
            client1.setDiscount(client.getDiscount());
            client1.setEmail(client.getEmail());
            client1.setPassword(passwordEncoder.encode(client.getPassword()));
            Role user = new Role();
			user.setId(1L);
            client1.setRole(user);
            

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
