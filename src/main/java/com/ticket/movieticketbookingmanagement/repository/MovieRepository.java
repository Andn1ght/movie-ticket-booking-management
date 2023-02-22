package com.ticket.movieticketbookingmanagement.repository;

import com.jfoenix.controls.JFXComboBox;
import com.ticket.movieticketbookingmanagement.model.Movie;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

public interface MovieRepository {

    void deleteAddMovie(String title);

    void updateAddMovies(String title, String genre, String duration, String image, String date) throws SQLException;

    void insertAddMovies(String movieTitle, String genre, String duration, String image, String date) throws SQLException;

    void movieId() throws SQLException;

    ObservableList<Movie> addMoviesList() throws SQLException;

    ObservableList<Movie> editScreeningList() throws SQLException;

    void updateEditScreening(String current, String title, ImageView image);
}
