package com.apirest.api.services;

import com.apirest.api.dto.ClientDTO;
import com.apirest.api.entities.Client;
import com.apirest.api.repositories.ClientRepository;
import com.apirest.api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
   public Page<ClientDTO> findAllClients(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findClientById(Long id){
        Optional<Client> result = Optional.ofNullable(repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id)));
        return new ClientDTO(result.get());
    }

    @Transactional
    public ClientDTO createClient(ClientDTO dto){
        try{
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
        }catch(ResourceNotFoundException e){
            throw new ResourceNotFoundException("Client not found with id " + dto.getId());
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteClientById(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Client not found with id " + id);
        }
        repository.deleteById(id);
    }

    @Transactional
    public ClientDTO updateClient(Long id, ClientDTO dto){
        try{
        Client entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
        }catch(Exception e){
            throw new ResourceNotFoundException("Client not found with id " + id);
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
