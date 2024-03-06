package com.example.market.sellings.controller.request;

import lombok.Data;

@Data
public class AddPaymentRequest {
    private Integer getPaidValue;
    private String getPaymentType;
}
