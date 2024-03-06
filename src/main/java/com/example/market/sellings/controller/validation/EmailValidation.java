package com.example.market.sellings.controller.validation;

public class EmailValidation {
    private static final String REGEX = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    public boolean validate(String email) {
        return email.matches(REGEX);
    }
}
