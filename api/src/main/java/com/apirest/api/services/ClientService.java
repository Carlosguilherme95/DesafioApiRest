package com.apirest.api.services;

import com.apirest.api.entities.Client;
import com.apirest.api.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional
   public List<Client> findAllClients(){
        return repository.findAll();
    }
}
