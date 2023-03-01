package com.anobel.service.impl;

import com.anobel.exception.NullEntityReferenceException;
import com.anobel.model.Client;
import com.anobel.repository.ClientRepository;
import com.anobel.repository.RoleRepository;
import com.anobel.service.ClientService;
import com.anobel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "ClientServiceImpl")
public class ClientServiceImpl implements ClientService {
    @Autowired
    private  ClientRepository clientRepository;

    @Autowired
    private RoleRepository roleRepository ;
	@Autowired
	private  PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public List<Client> getAllClients() {

        return clientRepository.findAll();

    }

    @Override
    public Client create(Client client) {
        if (client != null) {

            client.setRole(roleService.find(1L));
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
            client1.setRole(client.getRole());
            

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
