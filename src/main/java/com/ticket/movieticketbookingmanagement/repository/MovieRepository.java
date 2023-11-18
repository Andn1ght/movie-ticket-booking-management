package com.ticket.movieticketbookingmanagement.repository;

import com.ticket.movieticketbookingmanagement.model.Movie;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

public interface MovieRepository {

    void deleteAddMovie(String title);

    void updateAddMovies(String title, String genre, String duration, String image, String date);

    void insertAddMovies(String movieTitle, String genre, String duration, String image, String date);

    ObservableList<Movie> addMoviesList();

    ObservableList<Movie> editScreeningList();

    ObservableList<Movie> availableMoviesList();

    void updateEditScreening(String current, String title, ImageView image);

    void buyTicket(Float price1, Float price2, Float total, ImageView image, String title, int quantity);
}
