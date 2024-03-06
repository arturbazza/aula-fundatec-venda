package com.example.market.sellings.controller.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmailValidationTest {

    @Test
    void validateInValidEmail() {
        String email = "teste@.com";
        String email2 = "@gmail.com";
        String email3 = "teste@outlook.br.1.2.3.3;@";
        String email4 = "teste@outlook;br";

        EmailValidation emailValidation = new EmailValidation();
        // caso 1
        boolean result = emailValidation.validate(email);
        Assertions.assertFalse(result);
        // caso 2
        result = emailValidation.validate(email2);
        Assertions.assertFalse(result);
        //caso 3
        result = emailValidation.validate(email3);
        Assertions.assertFalse(result);

        //caso 4
        result = emailValidation.validate(email4);
        Assertions.assertFalse(result);
    }

    @Test
    void validateEmailValid() {
        String email = "teste@teste.com";
        String email2 = "aline@gmail.com";
        String email3 = "teste@outlook.br";

        EmailValidation emailValidation = new EmailValidation();
        // caso 1
        boolean result = emailValidation.validate(email);
        Assertions.assertTrue(result);
        // caso 2
        result = emailValidation.validate(email2);
        Assertions.assertTrue(result);
        //caso 3
        result = emailValidation.validate(email3);
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"teste@teste.com", "aline@gmail.com", "teste@outlook.br"})
    void validEmail(String email) {
        EmailValidation emailValidation = new EmailValidation();
        boolean result = emailValidation.validate(email);
        Assertions.assertTrue(result);
    }
}