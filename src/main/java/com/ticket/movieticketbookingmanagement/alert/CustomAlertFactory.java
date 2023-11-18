package com.ticket.movieticketbookingmanagement.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

public class CustomAlertFactory implements AlertFactory {

    @Override
    public Alert createAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert;
    }

    @Override
    public Optional<ButtonType> createConfirmationAlert(String title, String content) {
        Alert alert = createAlert(title, content);
        return alert.showAndWait();
    }

    @Override
    public void createExceptionAlert(Exception ex, String title, String content) {
        Alert alert = createAlert(title, content);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }

    @Override
    public void showErrorAlert(String title, String content) {
        Alert alert = createAlert(title, content);
        alert.showAndWait();
    }

    @Override
    public void showInformationAlert(String title, String content) {
        Alert alert = createAlert(title, content);
        alert.showAndWait();
    }
}