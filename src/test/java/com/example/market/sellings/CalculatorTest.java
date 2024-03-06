package com.example.market.sellings;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class CalculatorTest {

    @Test
    void somar() {
        // Given
        double a = 1;
        double b = 3;
        // When
        var resultado = Calculator.somar(a, b);

        // Then
        Assertions.assertEquals(4, resultado);
    }

    @Test
    void dividir() {
        // Given
        double a = 4;
        double b = 2;

        // When
        var resultado = Calculator.dividir(a, b);

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
                    Calculator.dividir(a, b);
                }
        );
    }
}