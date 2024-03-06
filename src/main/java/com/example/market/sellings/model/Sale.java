package com.example.market.sellings.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "venda")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataVenda;

    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public void checarOperacaoEmVenda() {
        if(status == Status.FINALIZADO) {
            throw new RuntimeException("Não pode operar em vendas finalizadas");
        }
    }

}