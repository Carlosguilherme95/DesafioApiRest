package com.apirest.api.services;

import com.apirest.api.dto.ClientDTO;
import com.apirest.api.entities.Client;
import com.apirest.api.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
   public List<ClientDTO> findAllClients(){
        List<Client> result = repository.findAll();
        return result.stream().map(x -> new ClientDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public ClientDTO findClientById(Long id){
        Optional<Client> result = repository.findById(id);
        return new ClientDTO(result.get());
    }

    @Transactional
    public ClientDTO createClient(ClientDTO dto){
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public void deleteClientById(Long id){
        Optional<Client> result = repository.findById(id);
        if(result.isPresent()){
            repository.deleteById(id);
        }
    }

    @Transactional
    public ClientDTO updateClient(Long id, ClientDTO dto){
        Client entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
