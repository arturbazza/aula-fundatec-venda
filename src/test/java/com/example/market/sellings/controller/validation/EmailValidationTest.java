package com.example.market.sellings.controller.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmailValidationTest {

    @ParameterizedTest
    @ValueSource(strings = {"teste@teste.com", "aline@gmail.com", "teste@outlook.br"})
    void validEmail(String email) {
        EmailValidation emailValidation = new EmailValidation();
        boolean result = emailValidation.validate(email);
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"teste@.com", "@gmail.com", "teste@outlook.br.1.2.3.3;@", "teste@outlook;br"})
    void invalidEmail(String email) {
        EmailValidation emailValidation = new EmailValidation();
        boolean result = emailValidation.validate(email);
        Assertions.assertFalse(result);
    }
}