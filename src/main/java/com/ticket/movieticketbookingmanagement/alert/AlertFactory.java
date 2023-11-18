package com.ticket.movieticketbookingmanagement.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public interface AlertFactory {
    Alert createAlert(String title, String content);
    Optional<ButtonType> createConfirmationAlert(String title, String content);
    void createExceptionAlert(Exception ex, String title, String content);
    void showErrorAlert(String title, String content);
    void showInformationAlert(String title, String content);
}