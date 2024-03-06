package com.example.market.sellings.controller.request;

import com.example.market.sellings.model.Cliente;
import jakarta.validation.constraints.Email;
import lombok.Data;

/**
 * {
 *     "nome": "Anderson",
 *     "identidade": "00000",
 *     "cpf": "0000001",
 *     "endereco": {
 *         "logradouro": "Rua Alberto",
 *         "bairro": "Jardim Carvalho"
 *     }
 * }
 */
@Data
public class ClientRequest {
    private String nome;
    private String identidade;;
    private String cpf;
    private AddressRequest endereco;

    @Email
    private String email;

    public Cliente toModel() {
        var cliente = new Cliente();

        cliente.setNome(this.nome);
        cliente.setIdentidade(this.identidade);
        cliente.setCpf(this.cpf);
        cliente.setAddress(this.endereco.toModel());
        return cliente;
    }

}
