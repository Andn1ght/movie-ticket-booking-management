package com.ticket.movieticketbookingmanagement;


import com.ticket.movieticketbookingmanagement.alert.AlertMaker;
import com.ticket.movieticketbookingmanagement.model.Movie;
import com.ticket.movieticketbookingmanagement.repository.MovieRepository;
import com.ticket.movieticketbookingmanagement.repository.MovieRepositoryImpl;
import com.ticket.movieticketbookingmanagement.util.DatabaseUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DashboardController implements Initializable {

    @FXML
    private JFXButton addMovie_importBtn;

    @FXML
    private JFXButton addMovies_btn;

    @FXML
    private TableColumn<Movie, String> addMovies_col_date;

    @FXML
    private TableColumn<Movie, String> addMovies_col_duration;

    @FXML
    private TableColumn<Movie, String> addMovies_col_genre;

    @FXML
    private TableColumn<Movie, String> addMovies_col_movieTitle;

    @FXML
    private JFXButton addMovies_clearBtn;

    @FXML
    private DatePicker addMovies_date;

    @FXML
    private JFXButton addMovies_deleteBtn;

    @FXML
    private JFXTextField addMovies_duration;

    @FXML
    private AnchorPane addMovies_form;

    @FXML
    private JFXTextField addMovies_genre;

    @FXML
    private ImageView addMovies_imageView;

    @FXML
    private JFXButton addMovies_insertBtn;

    @FXML
    private JFXTextField addMovies_movieTitle;

    @FXML
    private JFXTextField addMovies_search;

    @FXML
    private TableView<Movie> addMovies_tableView;

    @FXML
    private JFXButton addMovies_updateBtn;

    @FXML
    private JFXButton availableMovies_btn;

    @FXML
    private JFXButton availableMovies_buyBtn;

    @FXML
    private JFXButton availableMovies_clearBtn;

    @FXML
    private TableColumn<?, ?> availableMovies_col_genre;

    @FXML
    private TableColumn<?, ?> availableMovies_col_movieTitle;

    @FXML
    private TableColumn<?, ?> availableMovies_col_showingDate;

    @FXML
    private Label availableMovies_date;

    @FXML
    private AnchorPane availableMovies_form;

    @FXML
    private Label availableMovies_genre;

    @FXML
    private ImageView availableMovies_imageView;

    @FXML
    private Label availableMovies_movieTitle;

    @FXML
    private Label availableMovies_normalClass_price;

    @FXML
    private Spinner<?> availableMovies_normalClass_quantity;

    @FXML
    private JFXButton availableMovies_receiptBtn;

    @FXML
    private JFXButton availableMovies_selectMovieBtn;

    @FXML
    private Label availableMovies_specialClass_price;

    @FXML
    private Spinner<?> availableMovies_specialClass_quantity;

    @FXML
    private TableView<?> availableMovies_tableView;

    @FXML
    private Label availableMovies_title;

    @FXML
    private Label availableMovies_total;

    @FXML
    private JFXButton close;

    @FXML
    private JFXButton customers_btn;

    @FXML
    private JFXButton customers_clearBtn;

    @FXML
    private TableColumn<?, ?> customers_col_date;

    @FXML
    private TableColumn<?, ?> customers_col_genre;

    @FXML
    private TableColumn<?, ?> customers_col_movieTitle;

    @FXML
    private TableColumn<?, ?> customers_col_ticketNumber;

    @FXML
    private TableColumn<?, ?> customers_col_time;

    @FXML
    private Label customers_date;

    @FXML
    private JFXButton customers_deleteBtn;

    @FXML
    private AnchorPane customers_form;

    @FXML
    private Label customers_genre;

    @FXML
    private Label customers_movieTitle;

    @FXML
    private JFXTextField customers_search;

    @FXML
    private TableView<?> customers_tableView;

    @FXML
    private Label customers_ticketNumber;

    @FXML
    private Label customers_time;

    @FXML
    private Label dashboard_availableMovies;

    @FXML
    private JFXButton dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_totalEarnToday;

    @FXML
    private Label dashboard_totalSoldTicket;

    @FXML
    private JFXButton editScreening_btn;

    @FXML
    private TableColumn<Movie, String> editScreening_col_current;

    @FXML
    private TableColumn<Movie, String> editScreening_col_duration;

    @FXML
    private TableColumn<Movie, String> editScreening_col_genre;

    @FXML
    private TableColumn<Movie, String> editScreening_col_movieTitle;

    @FXML
    private JFXComboBox<?> editScreening_current;

    @FXML
    private JFXButton editScreening_deleteBtn;

    @FXML
    private AnchorPane editScreening_form;

    @FXML
    private ImageView editScreening_imageView;

    @FXML
    private JFXTextField editScreening_search;

    @FXML
    private TableView<Movie> editScreening_tableView;

    @FXML
    private Label editScreening_title;

    @FXML
    private JFXButton editScreening_updateBtn;

    @FXML
    private JFXButton signout;

    @FXML
    private Label username;

    private Image image;

    private double x = 0;
    private double y = 0;

    private Connection connect;


    public void updateEditScreening() throws SQLException {

        connect = DatabaseUtil.getConnection();
        MovieRepository movieRepository = new MovieRepositoryImpl(connect);
        if (editScreening_current.getSelectionModel().getSelectedItem() == null){
            AlertMaker.showErrorMessage("Error Message" , "Please select the current status");
        } else {
            movieRepository.updateEditScreening(editScreening_current.getSelectionModel().getSelectedItem().toString(), editScreening_title.getText(), editScreening_imageView);
            showEditScreening();
            clearEditScreening();
        }
    }

    public void clearEditScreening() {

        editScreening_title.setText("");
        editScreening_current.setSelectionModel(null);
        editScreening_imageView.setImage(null);

    }

    public void selectEditScreening() {

        Movie mov = editScreening_tableView.getSelectionModel().getSelectedItem();
        int num = editScreening_tableView.getSelectionModel().getFocusedIndex();

        if((num -1) < -1) {
            return;
        }

        String uri = "file:" + mov.getImage();
        image = new Image(uri, 132, 183, false, true);
        editScreening_imageView.setImage(image);

        editScreening_title.setText(mov.getTitle());
    }

    private String[] currentList = {"Showing", "End Showing"};
    public void editScreeningComboBox() {

        List<String> listCurrent = new ArrayList<>();

        for (String data: currentList) {

            listCurrent.add(data);

        }

        ObservableList listC = FXCollections.observableArrayList(listCurrent);
        editScreening_current.setItems(listC);
    }

    private ObservableList<Movie> editScreeningL;
    public void showEditScreening() throws SQLException {

        connect = DatabaseUtil.getConnection();
        MovieRepository movieRepository = new MovieRepositoryImpl(connect);

        editScreeningL = movieRepository.editScreeningList();

        editScreening_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        editScreening_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        editScreening_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        editScreening_col_current.setCellValueFactory(new PropertyValueFactory<>("current"));

        editScreening_tableView.setItems(editScreeningL);

    }



    public void searchAddMovies() {
        FilteredList<Movie> filter = new FilteredList<>(listAddMovies, e -> true);
        addMovies_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateMoviesData -> {
                if (newValue.isEmpty()){
                    return true;
                }
                String keySearch = newValue.toLowerCase();
                if (predicateMoviesData.getTitle().toLowerCase().contains(keySearch)){
                    return true;
                } else if (predicateMoviesData.getGenre().toLowerCase().contains(keySearch)){
                    return true;
                } else if (predicateMoviesData.getDuration().toLowerCase().contains(keySearch)){
                    return true;
                } else if (predicateMoviesData.getDate().toString().contains(keySearch)) {
                    return true;
                }
                return false;
            });
            // Add this line to apply the filter to the table view
            addMovies_tableView.setItems(filter);
        });
        // Add this line to apply the initial filter to the table view
        addMovies_tableView.setItems(filter);
    }



    public void importImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*png", "*jpg"));

        Stage stage = (Stage) addMovies_form.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if (file != null) {

            image = new Image(file.toURI().toString(), 127, 167, false, true);
            addMovies_imageView.setImage(image);
            // TO GET THE IMAGE PATH
            getData.path = file.getAbsolutePath();

        }

    }

    public void clearAddMoviesList(){

        addMovies_movieTitle.setText("");
        addMovies_genre.setText("");
        addMovies_imageView.setImage(null);
        addMovies_date.setValue(null);
        addMovies_duration.setText("");

    }

    public void updateAddMovies() throws SQLException {

        connect = DatabaseUtil.getConnection();
        MovieRepository movieRepository = new MovieRepositoryImpl(connect);

        try {

            String uri = getData.path;
            uri = uri.replace("\\", "\\\\");

            movieRepository.updateAddMovies(addMovies_movieTitle.getText(), addMovies_genre.getText(),
                    addMovies_duration.getText(), uri, String.valueOf(addMovies_date.getValue()));

            clearAddMoviesList();
            showAddMoviesList();

        } catch (Exception e) {e.printStackTrace();}


    }

    public void deleteAddMovie() throws SQLException {

        connect = DatabaseUtil.getConnection();
        MovieRepository movieRepository = new MovieRepositoryImpl(connect);
        movieRepository.deleteAddMovie(addMovies_movieTitle.getText());
        showAddMoviesList();
        clearAddMoviesList();
    }

    public void insertAddMovies() throws SQLException {

        connect = DatabaseUtil.getConnection();
        MovieRepository movieRepository = new MovieRepositoryImpl(connect);
        try {

            String uri = getData.path;
            uri = uri.replace("\\", "\\\\");
            movieRepository.insertAddMovies(addMovies_movieTitle.getText(), addMovies_genre.getText(), addMovies_duration.getText(),
                    uri, String.valueOf(addMovies_date.getValue()));


            clearAddMoviesList();
            showAddMoviesList();


        } catch (SQLException e) {e.printStackTrace();}

    }

    private ObservableList<Movie> listAddMovies;

    public void showAddMoviesList() throws SQLException {

        connect = DatabaseUtil.getConnection();
        MovieRepository movieRepository = new MovieRepositoryImpl(connect);
        listAddMovies = movieRepository.addMoviesList();

        addMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        addMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addMovies_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        addMovies_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        addMovies_tableView.setItems(listAddMovies);
    }

    public void selectAddMoviesList() {

        Movie mov = addMovies_tableView.getSelectionModel().getSelectedItem();
        int num = addMovies_tableView.getSelectionModel().getFocusedIndex();

        if ((num -1) < -1) {
            return;
        }

        getData.path = mov.getImage();

        getData.movieId = mov.getId();

        addMovies_movieTitle.setText(mov.getTitle());
        addMovies_genre.setText(mov.getGenre());
        addMovies_duration.setText(mov.getDuration());

        String getDate = String.valueOf(mov.getDate());

        String uri = "file:" + mov.getImage();

        image = new Image(uri, 127, 167, false, true);
        addMovies_imageView.setImage(image);

        addMovies_date.setValue(mov.getDate().atStartOfDay().toLocalDate());

    }

    public void logout(){

        signout.getScene().getWindow().hide();

        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginForm.fxml")));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) ->{

                x = event.getSceneX();
                y = event.getSceneY();

            });

            root.setOnMouseDragged((MouseEvent event) ->{

                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);

            });

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {e.printStackTrace();}
    }

    public void switchForm(ActionEvent event){

        if (event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            editScreening_btn.setStyle("-fx-background-color: transparent;");
            dashboard_btn.setStyle("-fx-background-color: #f0c419;");
            addMovies_btn.setStyle("-fx-background-color: transparent;");
            availableMovies_btn.setStyle("-fx-background-color: transparent;");
            customers_btn.setStyle("-fx-background-color: transparent;");
        } else if (event.getSource() == addMovies_btn) {
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(true);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            editScreening_btn.setStyle("-fx-background-color: transparent;");
            addMovies_btn.setStyle("-fx-background-color: #f0c419;");
            dashboard_btn.setStyle("-fx-background-color: transparent;");
            availableMovies_btn.setStyle("-fx-background-color: transparent;");
            customers_btn.setStyle("-fx-background-color: transparent;");
        } else if (event.getSource() == availableMovies_btn) {
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(true);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            editScreening_btn.setStyle("-fx-background-color: transparent;");
            availableMovies_btn.setStyle("-fx-background-color: #f0c419;");
            dashboard_btn.setStyle("-fx-background-color: transparent;");
            addMovies_btn.setStyle("-fx-background-color: transparent;");
            customers_btn.setStyle("-fx-background-color: transparent;");
        } else if (event.getSource() == editScreening_btn) {
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(true);
            customers_form.setVisible(false);

            availableMovies_btn.setStyle("-fx-background-color: transparent;");
            editScreening_btn.setStyle("-fx-background-color: #f0c419;");
            dashboard_btn.setStyle("-fx-background-color: transparent;");
            addMovies_btn.setStyle("-fx-background-color: transparent;");
            customers_btn.setStyle("-fx-background-color: transparent;");
        } else if (event.getSource() == customers_btn) {
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(true);

            availableMovies_btn.setStyle("-fx-background-color: transparent;");
            customers_btn.setStyle("-fx-background-color: #f0c419;");
            dashboard_btn.setStyle("-fx-background-color: transparent;");
            addMovies_btn.setStyle("-fx-background-color: transparent;");
            editScreening_btn.setStyle("-fx-background-color: transparent;");
        }
    }

    public void displayUsername(){

        username.setText(getData.username);

    }

    public void close(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayUsername();
        try {
            showAddMoviesList();
            showEditScreening();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        editScreeningComboBox();
    }
}
