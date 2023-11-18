package com.ticket.movieticketbookingmanagement.repository;

import com.ticket.movieticketbookingmanagement.alert.AlertFactory;
import com.ticket.movieticketbookingmanagement.alert.CustomAlertFactory;
import com.ticket.movieticketbookingmanagement.model.Customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository {

    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;
    private CustomAlertFactory alertFactory;

    public CustomerRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void deleteCustomer(String ticketNum, String title, String date, String time) {
        try {
            if (ticketNum.isEmpty() || title.isEmpty() || date.isEmpty() || time.isEmpty()) {
                alertFactory.createAlert("Error Message", "Please select the customer first!").showAndWait();
            } else {
                Optional<ButtonType> option = alertFactory
                        .createConfirmationAlert("Confirmation Message", "Are you sure you want to delete " + title);

                if (option.isPresent() && option.get() == ButtonType.OK) {
                    // Perform the deletion operation
                    String query = "DELETE FROM customer WHERE id = '" + ticketNum + "'";
                    prepare = connection.prepareStatement(query);
                    prepare.executeUpdate();
                    alertFactory.createAlert("Information Message", "Successfully removed " + title).showAndWait();
                }
            }
        } catch (Exception e) {
            alertFactory.createExceptionAlert(e, "Error Message", "An error occurred while deleting the customer");
        }
    }

    @Override
    public ObservableList<Customer> customerList() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String query = "SELECT * FROM customer";

        try {
            Customer customer;
            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();

            while (result.next()) {
                customer = new Customer(
                        result.getInt("id"),
                        result.getString("type"),
                        result.getString("movieTitle"),
                        result.getInt("quantity"),
                        result.getDouble("total"),
                        result.getDate("date"),
                        result.getString("time")
                );
                customerList.add(customer);
            }
        } catch (Exception e) {
            alertFactory.createExceptionAlert(e, "Error Message", "An error occurred while retrieving customer data.");
        }
        return customerList;
    }
}
