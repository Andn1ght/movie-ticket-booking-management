package com.ticket.movieticketbookingmanagement.validators;

public interface InputValidator {
    boolean validate(String input);
    void setNextValidator(InputValidator nextValidator);
}
