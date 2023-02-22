package com.ticket.movieticketbookingmanagement.validators;

public class PasswordValidator implements InputValidator {

    @Override
    public boolean validate(String input) {

        if (input.length() <= 8) {
            return false;
        } else {
            return true;
        }
    }
}
