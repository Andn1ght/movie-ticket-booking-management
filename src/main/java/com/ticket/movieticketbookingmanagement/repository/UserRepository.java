package com.ticket.movieticketbookingmanagement.repository;

import com.ticket.movieticketbookingmanagement.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserRepository {
    User signInUser(String username, String password);
    void addUser(User user) throws SQLException;

    boolean isUserExist(String username) throws SQLException;

    User getUserById(int id) throws SQLException;
    User getUserByEmail(String email) throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;
}
