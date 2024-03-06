package com.example.market.sellings.controller.response;

import com.example.market.sellings.model.Cliente;
import lombok.Data;

@Data
public class ClientResponse {
    private Long id;
    private String nome;
    private String identidade;;
    private String cpf;
    private AddressResponse endereco;

    public static ClientResponse fromModel(Cliente cliente) {
        var clienteResponse = new ClientResponse();
        clienteResponse.setId(cliente.getId());
        clienteResponse.setNome(cliente.getNome());
        clienteResponse.setIdentidade(cliente.getIdentidade());
        clienteResponse.setCpf(cliente.getCpf());
        clienteResponse.setEndereco(AddressResponse.fromModel(cliente.getAddress()));

        return clienteResponse;
    }

}
