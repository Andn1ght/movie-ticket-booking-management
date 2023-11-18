package com.ticket.movieticketbookingmanagement.alert;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class AlertMaker {

    private static final AlertFactory alertFactory = new CustomAlertFactory();

    public static void showSimpleAlert(String title, String content) {
        alertFactory.createAlert(title, content).showAndWait();
    }

    public static void showWarning(Exception ex) {
        showWarning("Warning", "An exception occurred: ", ex);
    }

    public static void showWarning(String title, String message, Exception ex) {
        Notifications notify = Notifications.create()
                .darkStyle()
                .title(title)
                .text(message + ex.getMessage())
                .position(Pos.TOP_CENTER)
                .hideAfter(Duration.seconds(5));
        notify.show();
    }

    public static void showErrorMessage(String title, String content) {
        alertFactory.showErrorAlert(title, content);
    }

    public static void showErrorMessage(Exception ex) {
        alertFactory.createExceptionAlert(ex, "Error occurred", ex.getLocalizedMessage());
    }

    public static void showErrorMessage(Exception ex, String title, String content) {
        alertFactory.createExceptionAlert(ex, title, content);
    }

    public static void showNotification(String title, String message) {
        Notifications notify = Notifications.create()
                .darkStyle()
                .title(title)
                .text(message)
                .position(Pos.TOP_CENTER)
                .hideAfter(Duration.seconds(5));
        notify.show();
    }
}