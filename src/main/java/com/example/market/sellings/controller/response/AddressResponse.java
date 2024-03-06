package com.example.market.sellings.controller.response;

import com.example.market.sellings.model.Address;
import lombok.Data;

@Data
public class AddressResponse {
    private Long id;
    private String street;
    private String neighborhood;

    public static AddressResponse fromModel(Address address) {
        var addressResponse = new AddressResponse();
        addressResponse.setId(address.getId());
        addressResponse.setStreet(address.getStreet());
        addressResponse.setNeighborhood(address.getNeighborhood());
        return addressResponse;
    }
}
