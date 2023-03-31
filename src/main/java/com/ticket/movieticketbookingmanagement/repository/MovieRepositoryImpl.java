package com.ticket.movieticketbookingmanagement.repository;

import com.ticket.movieticketbookingmanagement.alert.AlertMaker;
import com.ticket.movieticketbookingmanagement.getData;
import com.ticket.movieticketbookingmanagement.model.Movie;
import com.ticket.movieticketbookingmanagement.util.DatabaseUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MovieRepositoryImpl implements MovieRepository {

    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;

    public MovieRepositoryImpl (Connection connection) {
        this.connection = connection;
    }

    @Override
    public void deleteAddMovie(String title) {

        String query = "DELETE FROM movie WHERE movieTitle = '" + title + "'";

        try {

            prepare = connection.prepareStatement(query);

            if (title.isEmpty()) {

                AlertMaker.showErrorMessage("Error Message", "Please select the movie first");

            } else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure want to delete " + title +" ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (ButtonType.OK.equals(option.get())){

                    prepare.executeUpdate();
                    AlertMaker.showSimpleAlert("Information Message", "Successfully delete!");

                } else {
                    return;
                }

            }

        } catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public void updateAddMovies(String title, String genre, String duration, String image, String date) {

        String query = "UPDATE movie SET movieTitle = '" + title
                + "', genre = '" + genre
                + "', duration = '" + duration
                + "', image = '" + image
                + "', date = '" + date
                + "' WHERE id = '" + String.valueOf(getData.movieId) + "'";

        try {

            prepare = connection.prepareStatement(query);

            if (title.isEmpty() || genre.isEmpty() || duration.isEmpty() || image.isEmpty() || date.isEmpty()) {

                AlertMaker.showErrorMessage("Error Message", "Please select the movie first");

            } else {

                prepare.executeUpdate();
                AlertMaker.showSimpleAlert("Information Message", "Successfully update " + title + " !");

            }


        } catch (Exception e){e.printStackTrace();}

    }


    @Override
    public void insertAddMovies(String movieTitle, String genre, String duration, String image, String date) throws SQLException {
        String query1 = "SELECT movieTitle FROM movie WHERE movieTitle = '" + movieTitle + "'";

        try {

            prepare = connection.prepareStatement(query1);
            result = prepare.executeQuery();

            if (result.next()) {

                AlertMaker.showErrorMessage("Error Message", movieTitle + " already exists!");

            } else{

                if (movieTitle.isEmpty() || genre.isEmpty() || duration.isEmpty() || image.isEmpty() || date.isEmpty()) {

                    AlertMaker.showErrorMessage("Error Message", "Please fill all blank fields!");

                } else {

                    String query = "INSERT INTO movie (id, movieTitle, genre, duration, image, date) VALUES (?,?,?,?,?,?)";

                    movieId();

                    String mID = String.valueOf(getData.movieId + 1);

                    prepare = connection.prepareStatement(query);

                    prepare.setString(1, mID);
                    prepare.setString(2, movieTitle);
                    prepare.setString(3, genre);
                    prepare.setString(4, duration);
                    prepare.setString(5, image);
                    prepare.setString(6, date);
                    prepare.execute();

                    AlertMaker.showSimpleAlert("Information Message", "Successfully added new movie!");

                }
            }
        } catch (SQLException e) {e.printStackTrace();}
        finally {
            // close database resources
            if (result != null) result.close();
            if (prepare != null) prepare.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public void movieId() throws SQLException {

        String query = "SELECT count(id) FROM movie";

        connection = DatabaseUtil.getConnection();

        try {

            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();

            if (result.next()) {
                getData.movieId = result.getInt("count(id)");
            }

        } catch (Exception e) {e.printStackTrace();}


    }

    @Override
    public ObservableList<Movie> addMoviesList() throws SQLException {
        ObservableList<Movie> listData = FXCollections.observableArrayList();

        try {

            String query = "SELECT * FROM movie";

            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();

            while (result.next()) {
                Integer id = result.getInt("id");
                String title = result.getString("movieTitle");
                String genre = result.getString("genre");
                String duration = result.getString("duration");
                String image = result.getString("image");
                LocalDate date = result.getDate("date").toLocalDate();
                String current = result.getString("current");

                Movie movie = new Movie(id, title, genre, duration, image, date, current);
                listData.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (result != null) {
                result.close();
            }
            if (prepare != null) {
                prepare.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return listData;
    }

    @Override
    public ObservableList<Movie> editScreeningList() throws SQLException {

        ObservableList<Movie> editSList = FXCollections.observableArrayList();

        String query = "SELECT * FROM movie";

        try {

            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();

            while (result.next()) {

                Integer id = result.getInt("id");
                String title = result.getString("movieTitle");
                String genre = result.getString("genre");
                String duration = result.getString("duration");
                String image = result.getString("image");
                LocalDate date = result.getDate("date").toLocalDate();
                String current = result.getString("current");

                Movie movie = new Movie(id, title, genre, duration, image, date, current);
                editSList.add(movie);

            }

        } catch (Exception e) {e.printStackTrace();}
        finally {
            if (result != null) {
                result.close();
            }
            if (prepare != null) {
                prepare.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return editSList;
    }

    @Override
    public ObservableList<Movie> availableMoviesList() {

        ObservableList<Movie> listAvMovies = FXCollections.observableArrayList();

        String query = "SELECT * FROM movie WHERE current = 'Showing'";

        try {

            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();

            Movie mov;

            while (result.next()) {

                mov = new Movie(result.getInt("id"), result.getString("movieTitle")
                        , result.getString("genre"), result.getString("duration")
                        , result.getString("image"), result.getDate("date").toLocalDate()
                        , result.getString("current"));

                listAvMovies.add(mov);

            }

        } catch (Exception e ) {e.printStackTrace();}
        return listAvMovies;
    }



    @Override
    public void updateEditScreening(String current, String title, ImageView image) {

        if(title.isEmpty() || image.getImage() == null) {

            AlertMaker.showErrorMessage("Error Message", "Please select the movie first");

        }

        String query = "UPDATE movie SET current = '"
                + current + "' WHERE movieTitle = '" + title +"'";

        try {
                prepare = connection.prepareStatement(query);

                prepare.executeUpdate();
                AlertMaker.showSimpleAlert("Information Message", "Successfully update!");

            } catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public void buyTicket(Float price1, Float price2, Float total, ImageView image, String title, int quantity) {

        String query = "INSERT INTO customer (type, movieTitle, quantity, total, date, time) VALUES (?,?,?,?,?,?)";

        String type = "";
        if (price1 > 0 && price2 == 0) {
            type = "Special Class";
        } else if (price2 > 0 && price1 == 0) {
            type = "Normal Class";
        } else if (price2 > 0 && price1 > 0) {
            type = "Special & Normal Classes";
        }

        Date date = new Date();
        java.sql.Date setDate = new java.sql.Date(date.getTime());

        try {

            LocalTime localTime = LocalTime.now();

            Time time = Time.valueOf(localTime);

            prepare = connection.prepareStatement(query);
            prepare.setString(1, type);
            prepare.setString(2, title);
            prepare.setString(3, String.valueOf(quantity));
            prepare.setString(4, String.valueOf(total));
            prepare.setString(5, String.valueOf(setDate));
            prepare.setString(6, String.valueOf(time));

            if (image.getImage() == null || title.isEmpty()) {
                AlertMaker.showErrorMessage("Error Message", "Please select the movie first!");
            } else if (price1 == 0 && price2 == 0) {
                AlertMaker.showErrorMessage("Error Message", "Please indicate the quantity of ticket you want to purchase!");
            } else {

                prepare.executeUpdate();
                AlertMaker.showSimpleAlert("Information Message", "Successfully purchase!");

                String query1 = "SELECT * FROM customer";

                prepare = connection.prepareStatement(query1);
                result = prepare.executeQuery();

                int num = 0;

                while (result.next()) {
                    // GET THE LAST ID WE INSERTED
                    num = result.getInt("id");
                }

                String query2 = "INSERT INTO customer_info (customer_id, type, quantity, total, movieTitle) VALUES (?,?,?,?,?)";

                prepare = connection.prepareStatement(query2);
                prepare.setString(1, String.valueOf(num));
                prepare.setString(2, type);
                prepare.setString(3, String.valueOf(quantity));
                prepare.setString(4, String.valueOf(total));
                prepare.setString(5, title);
                prepare.execute();

            }

        } catch (Exception e) {e.printStackTrace();}


    }

}

