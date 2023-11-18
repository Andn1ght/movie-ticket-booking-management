package com.ticket.movieticketbookingmanagement.repository;

import com.ticket.movieticketbookingmanagement.model.Customer;
import javafx.collections.ObservableList;

public interface CustomerRepository {
    void deleteCustomer(String ticketNum, String title, String date, String time);
    ObservableList<Customer> customerList();
}
