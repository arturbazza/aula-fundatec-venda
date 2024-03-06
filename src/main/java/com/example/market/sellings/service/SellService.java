package com.example.market.sellings.service;

import com.example.market.sellings.model.*;
import com.example.market.sellings.repository.ProductRepository;
import com.example.market.sellings.repository.VendaProdutoRepository;
import com.example.market.sellings.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SellService {
    private final VendaRepository vendaRepository;
    private final ProductRepository productRepository;
    private final VendaProdutoRepository vendaProdutoRepository;

    @Transactional
    public void iniciarVenda(List<Long> idProdutos) {

        var venda = new Sale();
        venda.setDataVenda(LocalDateTime.now());
        venda.setStatus(Status.ABERTO);
        venda.setValorPago(BigDecimal.ZERO);
        venda.setPaymentType(null);
        // criar relacao produtos com vendas

        venda = vendaRepository.save(venda);

        Sale finalSale = venda;
        idProdutos.forEach(idProduto -> {
            Product product = productRepository.findById(idProduto).orElseThrow(() -> new IllegalArgumentException(
                    String.format("Produto %s não encontrado", idProduto)));
            ProductSale productSale = new ProductSale();
            productSale.setSale(finalSale);
            productSale.setProduct(product);
            ProductSaleId productSaleId = new ProductSaleId(product.getId(), finalSale.getId());
            productSale.setId(productSaleId);
            productSale.setQuantidade(1);
            productSale.setDataEntrada(LocalDateTime.now());
            vendaProdutoRepository.save(productSale);
        });


    }

    public void adicionarProdutoAVenda(List<Long> idProdutos, Long idVenda) {
        Sale sale = vendaRepository.findById(idVenda)
                .orElseThrow(() -> new RuntimeException(String.format("Venda de id %s não existe", idVenda)));
        // criar relacao produtos com vendas

        sale.checarOperacaoEmVenda();

        Sale finalSale = sale;
        idProdutos.forEach(idProduto -> {
            Product product = productRepository.findById(idProduto).orElseThrow(() -> new IllegalArgumentException(
                    String.format("Produto %s não encontrado", idProduto)));
            ProductSale productSale = new ProductSale();
            productSale.setSale(finalSale);
            productSale.setProduct(product);
            ProductSaleId productSaleId = new ProductSaleId(product.getId(), finalSale.getId());
            productSale.setId(productSaleId);
            productSale.setQuantidade(1);
            productSale.setDataEntrada(LocalDateTime.now());
            vendaProdutoRepository.save(productSale);
        });

    }

    public void removerProduto(List<Long> idProdutos, Long idVenda) {
        Sale sale = vendaRepository.findById(idVenda)
                .orElseThrow(() -> new RuntimeException(String.format("Venda de id %s não existe", idVenda)));

        sale.checarOperacaoEmVenda();
        // criar relacao produtos com vendas

        Sale finalSale = sale;
        idProdutos.forEach(idProduto -> {

            ProductSaleId productSaleId = new ProductSaleId(idProduto, finalSale.getId());

            ProductSale productSale = vendaProdutoRepository.findById(productSaleId)
                    .orElseThrow(() -> new RuntimeException(String.format("venda de produto não existe para essa venda %s produto %s", finalSale.getId(), idProduto)));

            vendaProdutoRepository.deleteById(productSaleId);
        });

    }

    public void finalizarVenda(Long idVenda) {
        Sale sale = vendaRepository.findById(idVenda)
                .orElseThrow(() -> new RuntimeException(String.format("Venda de id %s não existe", idVenda)));
        sale.checarOperacaoEmVenda();
        BigDecimal somaValorProdutos = somarValoresProdutosVenda(idVenda);

        if(!Objects.equals(sale.getValorPago(), somaValorProdutos)) {
            throw new RuntimeException("Inconsistencia nos valores da venda");
        }

        sale.setStatus(Status.FINALIZADO);
        vendaRepository.save(sale);

    }

    private BigDecimal somarValoresProdutosVenda(Long idVenda) {
        List<ProductSale> todasProductSale = vendaProdutoRepository.findAllByVendaId(idVenda);

        BigDecimal soma = BigDecimal.ZERO;
        for (ProductSale productSale : todasProductSale) {
            soma = soma.add(productSale.getProduct().getValor());
        }
        return soma;

    }

    public void addPayment(Long idVenda, Integer valorPago, String formaPagamento) {
        Sale sale = vendaRepository.findById(idVenda)
                .orElseThrow(() -> new RuntimeException(String.format("Venda de id %s não existe", idVenda)));

        sale.checarOperacaoEmVenda();

        sale.setPaymentType(PaymentType.valueOf(formaPagamento));
        sale.setValorPago(BigDecimal.valueOf(valorPago /100));
        vendaRepository.save(sale);
    }

    public List<ProductSale> listarVendas() {
        return vendaProdutoRepository.findAll();
    }

}
