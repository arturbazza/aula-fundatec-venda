package com.example.market.sellings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorTest {

    @Test
    void sum() {
        // Given
        double a = 1;
        double b = 3;
        // When
        var resultado = Calculator.sum(a, b);

        // Then
        assertThat(resultado).isEqualTo(4);
    }

    @Test
    void divide() {
        // Given
        double a = 4;
        double b = 2;

        // When
        var resultado = Calculator.divide(a, b);

        // Then
        assertThat(resultado).isEqualTo(2);
    }

    @Test
    void divideByZero() {
        // Given
        double a = 4;
        double b = 0;

        // When


        // Then
        assertThatThrownBy(
                () -> {
                    Calculator.divide(a, b);
                }
        ).isInstanceOf(ArithmeticException.class)
                .hasMessage("Não pode-se dividir por zero");
    }
}