package com.ticket.movieticketbookingmanagement.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends BaseValidator {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    @Override
    public boolean validate(String input) {
        boolean isValid = input.matches(EMAIL_REGEX);
        return isValid && super.validate(input);
    }
}
