package com.ticket.movieticketbookingmanagement.repository;

import com.ticket.movieticketbookingmanagement.alert.AlertMaker;
import com.ticket.movieticketbookingmanagement.model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class MovieRepositoryImpl implements MovieRepository {

    private Connection connection;

    public MovieRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void deleteAddMovie(String title) {
        try {
            if (title.isEmpty()) {
                AlertMaker.showErrorMessage("Error Message", "Please select the movie first");
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure want to delete " + title + " ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.orElse(null) == ButtonType.OK) {
                String query = "DELETE FROM movie WHERE movieTitle = ?";
                try (PreparedStatement prepare = connection.prepareStatement(query)) {
                    prepare.setString(1, title);
                    prepare.executeUpdate();
                    AlertMaker.showSimpleAlert("Information Message", "Successfully deleted!");
                }
            }
        } catch (SQLException e) {
            AlertMaker.showErrorMessage(e, "Error Message", "An error occurred while deleting movie.");
        }
    }

    @Override
    public void updateAddMovies(String title, String genre, String duration, String image, String date) {
        try {
            if (title.isEmpty() || genre.isEmpty() || duration.isEmpty() || image.isEmpty() || date.isEmpty()) {
                AlertMaker.showErrorMessage("Error Message", "Please fill all blank fields!");
                return;
            }

            String query = "UPDATE movie SET genre = ?, duration = ?, image = ?, date = ? WHERE movieTitle = ?";
            try (PreparedStatement prepare = connection.prepareStatement(query)) {
                prepare.setString(1, genre);
                prepare.setString(2, duration);
                prepare.setString(3, image);
                prepare.setString(4, date);
                prepare.setString(5, title);
                prepare.executeUpdate();
                AlertMaker.showSimpleAlert("Information Message", "Successfully updated " + title + " !");
            }
        } catch (SQLException e) {
            AlertMaker.showErrorMessage(e, "Error Message", "An error occurred while updating movie.");
        }
    }

    @Override
    public void insertAddMovies(String movieTitle, String genre, String duration, String image, String date) {
        try {
            if (movieTitle.isEmpty() || genre.isEmpty() || duration.isEmpty() || image.isEmpty() || date.isEmpty()) {
                AlertMaker.showErrorMessage("Error Message", "Please fill all blank fields!");
                return;
            }

            String query1 = "SELECT movieTitle FROM movie WHERE movieTitle = ?";
            try (PreparedStatement prepare = connection.prepareStatement(query1)) {
                prepare.setString(1, movieTitle);
                try (ResultSet result = prepare.executeQuery()) {
                    if (result.next()) {
                        AlertMaker.showErrorMessage("Error Message", movieTitle + " already exists!");
                    } else {
                        String query = "INSERT INTO movie (movieTitle, genre, duration, image, date) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement insertPrepare = connection.prepareStatement(query)) {
                            insertPrepare.setString(1, movieTitle);
                            insertPrepare.setString(2, genre);
                            insertPrepare.setString(3, duration);
                            insertPrepare.setString(4, image);
                            insertPrepare.setString(5, date);
                            insertPrepare.executeUpdate();
                            AlertMaker.showSimpleAlert("Information Message", "Successfully added new movie!");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            AlertMaker.showErrorMessage(e, "Error Message", "An error occurred while adding a new movie.");
        }
    }

    @Override
    public ObservableList<Movie> addMoviesList() {
        ObservableList<Movie> listData = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM movie";
            try (PreparedStatement prepare = connection.prepareStatement(query);
                 ResultSet result = prepare.executeQuery()) {
                while (result.next()) {
                    Movie movie = createMovieFromResultSet(result);
                    listData.add(movie);
                }
            }
        } catch (SQLException e) {
            AlertMaker.showErrorMessage(e, "Error Message", "An error occurred while retrieving movie data.");
        }
        return listData;
    }

    @Override
    public ObservableList<Movie> editScreeningList() {
        return addMoviesList(); // Reusing the method for simplicity, customize if needed
    }

    @Override
    public ObservableList<Movie> availableMoviesList() {
        ObservableList<Movie> listAvMovies = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM movie WHERE current = 'Showing'";
            try (PreparedStatement prepare = connection.prepareStatement(query);
                 ResultSet result = prepare.executeQuery()) {
                while (result.next()) {
                    Movie movie = createMovieFromResultSet(result);
                    listAvMovies.add(movie);
                }
            }
        } catch (SQLException e) {
            AlertMaker.showErrorMessage(e, "Error Message", "An error occurred while retrieving available movies.");
        }
        return listAvMovies;
    }

    @Override
    public void updateEditScreening(String current, String title, ImageView image) {
        if (title.isEmpty() || image.getImage() == null) {
            AlertMaker.showErrorMessage("Error Message", "Please select the movie first");
            return;
        }

        String query = "UPDATE movie SET current = ? WHERE movieTitle = ?";
        try (PreparedStatement prepare = connection.prepareStatement(query)) {
            prepare.setString(1, current);
            prepare.setString(2, title);
            prepare.executeUpdate();
            AlertMaker.showSimpleAlert("Information Message", "Successfully updated!");
        } catch (SQLException e) {
            AlertMaker.showErrorMessage(e, "Error Message", "An error occurred while updating movie screening status.");
        }
    }

    @Override
    public void buyTicket(Float price1, Float price2, Float total, ImageView image, String title, int quantity) {
        String query = "INSERT INTO customer (type, movieTitle, quantity, total, date, time) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            String type = determineTicketType(price1, price2);
            if (image.getImage() == null || title.isEmpty()) {
                AlertMaker.showErrorMessage("Error Message", "Please select the movie first!");
            } else if (quantity == 0) {
                AlertMaker.showErrorMessage("Error Message", "Please indicate the quantity of tickets you want to purchase!");
            } else {
                LocalTime localTime = LocalTime.now();
                Time time = Time.valueOf(localTime);

                try (PreparedStatement prepare = connection.prepareStatement(query)) {
                    prepare.setString(1, type);
                    prepare.setString(2, title);
                    prepare.setString(3, String.valueOf(quantity));
                    prepare.setString(4, String.valueOf(total));
                    prepare.setDate(5, Date.valueOf(LocalDate.now()));
                    prepare.setTime(6, time);
                    prepare.executeUpdate();

                    AlertMaker.showSimpleAlert("Information Message", "Successfully purchased!");
                }
            }
        } catch (SQLException e) {
            AlertMaker.showErrorMessage(e, "Error Message", "An error occurred while purchasing tickets.");
        }
    }

    private String determineTicketType(Float price1, Float price2) {
        String type = "";
        if (price1 > 0 && price2 == 0) {
            type = "Special Class";
        } else if (price2 > 0 && price1 == 0) {
            type = "Normal Class";
        } else if (price2 > 0 && price1 > 0) {
            type = "Special & Normal Classes";
        }
        return type;
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private Movie createMovieFromResultSet(ResultSet result) throws SQLException {
        return new Movie(
                result.getInt("id"),
                result.getString("movieTitle"),
                result.getString("genre"),
                result.getString("duration"),
                result.getString("image"),
                result.getDate("date").toLocalDate(),
                result.getString("current")
        );
    }
}
