package com.ticket.movieticketbookingmanagement.repository;

import com.ticket.movieticketbookingmanagement.alert.AlertMaker;
import com.ticket.movieticketbookingmanagement.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository{


    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;

    public CustomerRepositoryImpl (Connection connection) {
        this.connection = connection;
    }


    @Override
    public void deleteCustomer(String ticketNum, String title, String date, String time) {

        String query = "DELETE FROM customer WHERE id = '" + ticketNum +"'";


        try {

            prepare = connection.prepareStatement(query);

            if (ticketNum.isEmpty() || title.isEmpty() || date.isEmpty() || time.isEmpty()) {
                AlertMaker.showErrorMessage("Error Message", "Please select the customer first!");
            } else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete " + title + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK) {
                    prepare.executeUpdate();
                    AlertMaker.showSimpleAlert("Information Message", "Successfully removed " + title);
                } else {
                    return;
                }
            }

        } catch (Exception e) {e.printStackTrace();}

    }


    @Override
    public ObservableList<Customer> customerList() {

        ObservableList<Customer> customerL = FXCollections.observableArrayList();

        String query = "SELECT * FROM customer";

        try {

            Customer customer;

            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();

            while (result.next()) {

                customer = new Customer(result.getInt("id")
                        , result.getString("type")
                        , result.getString("movieTitle")
                        , result.getInt("quantity")
                        , result.getDouble("total")
                        , result.getDate("date")
                        , result.getTime("time"));

                customerL.add(customer);

            }

        } catch (Exception e) {e.printStackTrace();}
        return customerL;
    }
}
