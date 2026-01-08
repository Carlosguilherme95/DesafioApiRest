package com.apirest.api.services;

import com.apirest.api.dto.ClientDTO;
import com.apirest.api.entities.Client;
import com.apirest.api.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
   public List<ClientDTO> findAllClients(){
        List<Client> result = repository.findAll();
        return result.stream().map(x -> new ClientDTO(x)).toList();
    }
}
