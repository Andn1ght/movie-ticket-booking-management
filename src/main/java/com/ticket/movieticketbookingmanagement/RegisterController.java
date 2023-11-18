package com.ticket.movieticketbookingmanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.ticket.movieticketbookingmanagement.alert.AlertMaker;
import com.ticket.movieticketbookingmanagement.getData;
import com.ticket.movieticketbookingmanagement.model.User;
import com.ticket.movieticketbookingmanagement.repository.UserRepository;
import com.ticket.movieticketbookingmanagement.repository.UserRepositoryImpl;
import com.ticket.movieticketbookingmanagement.util.DatabaseUtil;
import com.ticket.movieticketbookingmanagement.validators.EmailValidator;
import com.ticket.movieticketbookingmanagement.validators.InputValidator;
import com.ticket.movieticketbookingmanagement.validators.PasswordValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class RegisterController {

    @FXML
    private JFXButton signin_close;

    @FXML
    private Hyperlink signin_createAccount;

    @FXML
    private AnchorPane signin_form;

    @FXML
    private JFXButton signin_loginBtn;

    @FXML
    private JFXPasswordField signin_password;

    @FXML
    private JFXTextField signin_username;

    @FXML
    private Hyperlink signup_alreadyHaveAccount;

    @FXML
    private JFXButton signup_btn;

    @FXML
    private JFXButton signup_close;

    @FXML
    private JFXTextField signup_email;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private JFXPasswordField signup_password;

    @FXML
    private JFXTextField signup_username;

    @FXML
    private ImageView ticket;

    @FXML
    private ImageView user;

    //TOOLS FOR DATABASE
    private Connection connection;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;


    public void signUp() {

        String username = signup_username.getText();
        String password = signup_password.getText();
        String email = signup_email.getText();

        InputValidator emailValidator = new EmailValidator();
        boolean isEmailValid = emailValidator.validate(signup_email.getText());

        InputValidator passwordValidator = new PasswordValidator();
        boolean isPasswordValid = passwordValidator.validate(signup_password.getText());

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {

            AlertMaker.showErrorMessage("Error Message", "Please fill all fields");
            return;
        }

        try {

            connection = DatabaseUtil.getConnection();
            UserRepository userRepository = new UserRepositoryImpl(connection);
            User user = new User(signup_username.getText(), signup_email.getText(), signup_password.getText());

            if (!isPasswordValid) {
                AlertMaker.showErrorMessage("Error Message", "Invalid Password");

            } else{

                if (isEmailValid) {

                    if (userRepository.isUserExist(username)){

                        AlertMaker.showErrorMessage("Error Message", username + " was already exist!");
                    } else {

                        userRepository.addUser(user);
                        AlertMaker.showSimpleAlert("Information Message", "Successfully create a new account!");

                        // AFTER SUCCESSFUL OF CREATING NEW ACCOUNT, IW WILL CLEAR THE TEXTFIELD
                        signup_email.setText("");
                        signup_password.setText("");
                        signup_username.setText("");

                    }
                } else {
                    AlertMaker.showErrorMessage("Error Message", "Invalid email");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private double x = 0;
    private double y = 0;
    public void signIn() {
        String username = signin_username.getText();
        String password = signin_password.getText();

        // CHECK IF THE USERNAME OR PASSWORD IS EMPTY
        if (username.isEmpty() || password.isEmpty()) {

            AlertMaker.showErrorMessage("Error Message", "Please fill all fields");
            return;
        }

        try {

            connection = DatabaseUtil.getConnection();
            UserRepository userRepository = new UserRepositoryImpl(connection);
            User user = userRepository.signInUser(username, password);

            if (user == null) {

                AlertMaker.showErrorMessage("Error Message", "Wrong Username/Password");

            } else {

                getData.username = username;

                AlertMaker.showSimpleAlert("Information Message", "Successfully Login!");

                // TO HIDE THE LOGIN FORM
                signin_loginBtn.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard.fxml")));

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

            }
        } catch (SQLException e) {e.printStackTrace();}
        catch (IOException e) {throw new RuntimeException(e);}
    }


    public void switchForm(ActionEvent event){

        if(event.getSource() == signin_createAccount){

            signin_form.setVisible(false);
            signup_form.setVisible(true);

        }else if(event.getSource() == signup_alreadyHaveAccount){

            signin_form.setVisible(true);
            signup_form.setVisible(false);

        }

    }

    public void close() {
        System.exit(0);
    }

}