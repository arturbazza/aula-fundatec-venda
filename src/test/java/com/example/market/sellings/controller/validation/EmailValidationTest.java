package com.example.market.sellings.controller.validation;

import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

class EmailValidationTest {

    @ParameterizedTest
    @ValueSource(strings = {"teste@teste.com", "aline@gmail.com", "teste@outlook.br"})
    void validEmail(String email) {
        EmailValidation emailValidation = new EmailValidation();
        boolean result = emailValidation.validate(email);
//        Assertions.assertTrue(result);
//        MatcherAssert.assertThat("email should be valid", result, Matchers.is(true));
        Assertions.assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"teste@.com", "@gmail.com", "teste@outlook.br.1.2.3.3;@", "teste@outlook;br"})
    void invalidEmail(String email) {
        EmailValidation emailValidation = new EmailValidation();
        boolean result = emailValidation.validate(email);
//        Assertions.assertFalse(result);
//        MatcherAssert.assertThat("email should be invalid", result, Matchers.is(false));
        Assertions.assertThat(result).isFalse();
    }

    @ParameterizedTest
    @MethodSource(value = "emailsFactory")
    void invalidEmailWithMethodSource(String email, boolean isValid) {
        EmailValidation emailValidation = new EmailValidation();
        boolean result = emailValidation.validate(email);
//        Assertions.assertEquals(result, isValid);
        Assertions.assertThat(result).isEqualTo(isValid);
    }

    public static Stream<Arguments> emailsFactory() {
        return Stream.of(
                Arguments.of("teste@.com", false),
                Arguments.of("teste@teste.com", true),
                Arguments.of("aline@gmail.com", true)
        );
    }


}