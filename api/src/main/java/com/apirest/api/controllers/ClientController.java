package com.apirest.api.controllers;
import com.apirest.api.dto.ClientDTO;
import com.apirest.api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientService service;


    @GetMapping
    public List<ClientDTO> findAll() {
        return service.findAllClients();
    }

    @GetMapping(value = "/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        return service.findClientById(id);
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO dto) {
        return service.createClient(dto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteClient(@PathVariable Long id){
         service.deleteClientById(id);
    }

    @PutMapping(value = "/{id}")
    public ClientDTO updateClient(@PathVariable Long id,@RequestBody ClientDTO dto) {
        return service.updateClient(id, dto);
    }
}
