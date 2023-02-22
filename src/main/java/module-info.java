module com.ticket.movieticketbookingmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires com.jfoenix;
    requires java.sql;
    requires controlsfx;
    requires fontawesomefx;

    opens com.ticket.movieticketbookingmanagement to javafx.fxml;
    exports com.ticket.movieticketbookingmanagement;
    exports com.ticket.movieticketbookingmanagement.model;
}