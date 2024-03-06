package com.example.market.sellings.repository;

import com.example.market.sellings.model.ProductSale;
import com.example.market.sellings.model.ProductSaleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendaProdutoRepository extends JpaRepository<ProductSale, ProductSaleId> {

    @Query("select v from VendaProduto v where v.id.vendaId = :idVenda")
    List<ProductSale> findAllByVendaId(Long idVenda);
}
