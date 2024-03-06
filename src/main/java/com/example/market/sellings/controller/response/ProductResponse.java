package com.example.market.sellings.controller.response;

import com.example.market.sellings.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String nome;
    private String gtin;
    private String descricao;
    private LocalDateTime dataFabricacao;
    private BigDecimal valor;

    public static ProductResponse fromModel(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setNome(product.getNome());
        response.setGtin(product.getGtin());
        response.setDescricao(product.getDescricao());
        response.setDataFabricacao(product.getDataFabricacao());
        response.setValor(product.getValor());
        return response;
    }

    public static List<ProductResponse> fromModel(List<Product> products) {
        return products.stream()
                .map(ProductResponse::fromModel)
                .toList();
    }
}
