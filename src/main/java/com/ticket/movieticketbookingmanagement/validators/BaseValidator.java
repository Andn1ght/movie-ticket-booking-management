package com.ticket.movieticketbookingmanagement.validators;

public abstract class BaseValidator implements InputValidator {

    private InputValidator nextValidator;

    @Override
    public boolean validate(String input) {
        if (nextValidator != null) {
            return nextValidator.validate(input);
        }
        return true;  // No more validators in the chain
    }

    @Override
    public void setNextValidator(InputValidator nextValidator) {
        this.nextValidator = nextValidator;
    }
}
