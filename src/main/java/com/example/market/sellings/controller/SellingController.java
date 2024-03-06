package com.example.market.sellings.controller;

import com.example.market.sellings.controller.request.AddPaymentRequest;
import com.example.market.sellings.controller.request.ProductId;
import com.example.market.sellings.controller.request.ProductIdRequest;
import com.example.market.sellings.controller.response.SaleResponse;
import com.example.market.sellings.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/vendas")
@RequiredArgsConstructor
public class SellingController {

    private final SellService sellService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void iniciarVenda(
            @RequestBody ProductIdRequest productIdRequest
    ) {
        List<Long> idsProduto = productIdRequest.getGetProducts()
                .stream()
                .map(ProductId::getIdProduto)
                .toList();

        sellService.iniciarVenda(idsProduto);
    }

    @PostMapping("/{idVenda}/products")
    public void addProduct(
            @PathVariable("idVenda") Long idVenda,
            @RequestBody ProductIdRequest productIdRequest
    ) {

        System.out.println("Adicionar Produtos a venda");
        List<Long> idsProduto = productIdRequest.getGetProducts()
                .stream()
                .map(ProductId::getIdProduto)
                .toList();

        sellService.adicionarProdutoAVenda(idsProduto, idVenda);

    }


    @DeleteMapping("/{idVenda}/produtos")
    public void removerProdutosDeVenda(
            @PathVariable("idVenda") Long idVenda,
            @RequestBody ProductIdRequest productIdRequest
    ) {

        System.out.println("Remover Produtos a venda");

        List<Long> productIds = productIdRequest.getGetProducts()
                .stream()
                .map(ProductId::getIdProduto)
                .toList();

        sellService.removerProduto(productIds, idVenda);

    }

    @PostMapping("/{idVenda}/pagamentos")
    public void adicionarPagamento(
            @PathVariable("idVenda") Long idVenda,
            @RequestBody AddPaymentRequest addPaymentRequest
    ) {

        System.out.println("Adicionar Pagamentos a venda");

        sellService.addPayment(idVenda, addPaymentRequest.getGetPaidValue(), addPaymentRequest.getGetPaymentType());

    }

    @PostMapping("/{idVenda}/finalizar")
    public void finalizarVenda(
            @PathVariable("idVenda") Long idVenda
    ) {

        System.out.println("Finalizar Venda");

        sellService.finalizarVenda(idVenda);

    }

    @GetMapping
    public List<SaleResponse> listarVendas() {
        return SaleResponse.fromModel(sellService.listarVendas());
    }


}
