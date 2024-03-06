package com.example.market.sellings.service;

import com.example.market.sellings.repository.AddressRepository;
import com.example.market.sellings.repository.ClientRepository;
import com.example.market.sellings.model.Cliente;
import com.example.market.sellings.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public List<Cliente> listClients() {
        return clientRepository.findAll();
    }

    public void createNewClient(Cliente cliente) {
        Address address = cliente.getAddress();
        addressRepository.save(address);
        clientRepository.save(cliente);
    }

    public void editClient(Long id, Cliente cliente) {
        validateId(id);
        cliente.setId(id);

        Address addressExistente = addressRepository.findByClienteId(id);

        if(addressExistente != null) {
            cliente.getAddress().setId(addressExistente.getId());
            if(!addressExistente.getNeighborhood()
                    .equalsIgnoreCase(cliente.getAddress().getNeighborhood())) {
                throw new RuntimeException("Não pode trocar o bairro de um endereço existente");
            }
        }


        addressRepository.save(cliente.getAddress());

        clientRepository.save(cliente);
    }

    private void validateId(Long id) {
        clientRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException(String.format("Cliente de id %s não existe", id)));
    }

    public void deleteClient(Long id) {
        validateId(id);
        clientRepository.deleteById(id);
    }

}
