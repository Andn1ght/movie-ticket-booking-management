package com.ticket.movieticketbookingmanagement;

import com.ticket.movieticketbookingmanagement.util.DatabaseUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class TicketMain extends Application {

    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginForm.fxml")));
        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) ->{

            x = event.getSceneX();
            y = event.getSceneY();

        });

        root.setOnMouseDragged((MouseEvent event) ->{

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });

        try {
            if (!DatabaseUtil.tableExists("user") || !DatabaseUtil.tableExists("movie")) {
                DatabaseUtil.createTables();
                System.out.println("Tables created successfully");
            } else {
                System.out.println("Tables already exist");
            }
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}