package com.ticket.movieticketbookingmanagement.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements InputValidator {

    @Override
    public boolean validate(String input) {

        // example_123@mail.com                  [FIRST LETTER][SECOND -> BEFORE @][@][example] [.] [com]
        Pattern pattern = Pattern.compile("[a-zA-z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher match = pattern.matcher(input);

        if (match.find() && match.group().matches(input)) {
            return true;
        } else {
            return false;
        }
    }
}
