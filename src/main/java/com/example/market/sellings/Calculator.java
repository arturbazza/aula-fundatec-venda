package com.example.market.sellings;

public class Calculator {
    public static double somar(double a, double b) {
        return a + b;
    }

    public static double dividir(double a, double b) {
        if (b == 0) {
           throw new ArithmeticException("Não pode-se dividir por zero");
        }
        return a / b;
    }
}
