package com.example.market.sellings.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "venda_produto")
public class ProductSale {

    @EmbeddedId
    private ProductSaleId id;

    @ManyToOne
    @MapsId(value = "venda_id")
    @JoinColumn(name = "venda_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_venda_produto_venda"))
    private Sale sale;

    @ManyToOne
    @MapsId(value = "produto_id")
    @JoinColumn(name = "produto_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_venda_produto_produto"))
    private Product Product;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private LocalDateTime dataEntrada;
}