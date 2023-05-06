package com.anobel.service.impl;

import com.anobel.model.Client;
import com.anobel.repository.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    private final ClientRepository clientRepository;

    public UserDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client founded = clientRepository.findClientByLogin(login);
        if(founded == null){
            throw new UsernameNotFoundException("User not found");
        }



        return null;
    }
}
