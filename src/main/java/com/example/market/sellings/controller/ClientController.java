package com.example.market.sellings.controller;

import com.example.market.sellings.controller.request.ClientRequest;
import com.example.market.sellings.controller.response.ClientResponse;
import com.example.market.sellings.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody ClientRequest clientRequest) {

        clientService.createNewClient(clientRequest.toModel());
    }

    @GetMapping
    public List<ClientResponse> listClients() {
        return clientService.listClients()
                .stream()
                .map(ClientResponse::fromModel)
                .toList();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @PutMapping(path = "/{id}")
    public void editClient(@PathVariable Long id, @RequestBody ClientRequest clientRequest) {
        System.out.println("Editando: ");
        System.out.println(clientRequest);

        clientService.editClient(id, clientRequest.toModel());
    }
}
