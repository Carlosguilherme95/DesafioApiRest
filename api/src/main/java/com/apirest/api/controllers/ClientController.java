package com.apirest.api.controllers;
import com.apirest.api.dto.ClientDTO;
import com.apirest.api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
