package com.example.market.sellings.controller.request;

import lombok.Data;

import java.util.List;

@Data
public class ProductIdRequest {
    private List<ProductId> getProducts;
}
