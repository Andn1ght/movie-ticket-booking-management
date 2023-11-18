package com.ticket.movieticketbookingmanagement.validators;

public class PasswordValidator extends BaseValidator {

    @Override
    public boolean validate(String input) {
        boolean hasMinLength = hasMinLength(input);
        boolean hasUppercase = containsUppercase(input);
        boolean hasLowercase = containsLowercase(input);
        boolean hasDigit = containsDigit(input);
        boolean hasSpecialChar = containsSpecialChar(input);

        return hasMinLength && hasUppercase && hasLowercase && hasDigit && hasSpecialChar && super.validate(input);
    }

    private boolean hasMinLength(String input) {
        return input.length() >= 8;
    }

    private boolean containsUppercase(String input) {
        return input.matches(".*[A-Z].*");
    }

    private boolean containsLowercase(String input) {
        return input.matches(".*[a-z].*");
    }

    private boolean containsDigit(String input) {
        return input.matches(".*\\d.*");
    }

    private boolean containsSpecialChar(String input) {
        return input.matches(".*[!@#$%^&*()-_=+\\\\|[{]};:'\",<.>/?].*");
    }
}
