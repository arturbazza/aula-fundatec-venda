package com.example.market.sellings.controller.request;

import com.example.market.sellings.model.Address;
import lombok.*;

@Data
public class AddressRequest {
    private String logradouro;
    private String bairro;

    public Address toModel() {
        var endereco = new Address();
        endereco.setStreet(logradouro);
        endereco.setNeighborhood(bairro);
        return endereco;
    }
}
