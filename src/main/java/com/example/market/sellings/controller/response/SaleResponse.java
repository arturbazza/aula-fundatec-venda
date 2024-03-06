package com.example.market.sellings.controller.response;

import com.example.market.sellings.model.PaymentType;
import com.example.market.sellings.model.ProductSale;
import com.example.market.sellings.model.Sale;
import com.example.market.sellings.model.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class SaleResponse {

    private Long id;
    private LocalDateTime sellDate;
    private BigDecimal paidValue;
    private PaymentType paymentType;
    private Status status;

    private List<ProductResponse> products;

    public static List<SaleResponse> fromModel(List<ProductSale> sellProducts) {


        Map<Sale, List<ProductSale>> collect = sellProducts.stream()
                .collect(Collectors.groupingBy(ProductSale::getSale));

        return collect.entrySet().stream().map(
                        (entry) ->  {
                            SaleResponse saleResponse = new SaleResponse();
                            saleResponse.setId(entry.getKey().getId());
                            saleResponse.setSellDate(entry.getKey().getDataVenda());
                            saleResponse.setPaidValue(entry.getKey().getValorPago());
                            saleResponse.setPaymentType(entry.getKey().getPaymentType());
                            saleResponse.setStatus(entry.getKey().getStatus());
                            var products = entry.getValue().stream()
                                    .map(ProductSale::getProduct)
                                    .toList();
                            saleResponse.setProducts(ProductResponse.fromModel(products));
                            return saleResponse;
                        }
                )
                .toList();
    }
}
