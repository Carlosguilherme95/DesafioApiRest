package com.apirest.api.controllers;
import com.apirest.api.dto.ClientDTO;
import com.apirest.api.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService service;


    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
        Page<ClientDTO> dto =  service.findAllClients(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        ClientDTO dto = service.findClientById(id);
        return ResponseEntity.ok(dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ClientDTO createClient(@Valid @RequestBody ClientDTO dto) {
        return service.createClient(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
         service.deleteClientById(id);
         return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id,@RequestBody ClientDTO dto) {
        dto = service.updateClient(id, dto);
        return ResponseEntity.ok(dto);
    }
}
