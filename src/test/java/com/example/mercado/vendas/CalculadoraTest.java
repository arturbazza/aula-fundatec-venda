package com.example.mercado.vendas;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class CalculadoraTest {

    @Test
    void somar() {
        // Given
        double a = 1;
        double b = 3;
        // When
        var resultado = Calculadora.somar(a, b);

        // Then
        Assertions.assertEquals(4, resultado);
    }

    @Test
    void dividir() {
        // Given
        double a = 4;
        double b = 2;

        // When
        var resultado = Calculadora.dividir(a, b);

        // Then
        Assertions.assertEquals(2, resultado);
    }

    @Test
    void dividirComZero() {
        // Given
        double a = 4;
        double b = 0;

        // When


        // Then
        Assertions.assertThrows(ArithmeticException.class,
                () -> {
                    Calculadora.dividir(a, b);
                }
        );
    }
}