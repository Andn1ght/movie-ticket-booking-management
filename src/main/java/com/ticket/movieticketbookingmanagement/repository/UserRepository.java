package com.ticket.movieticketbookingmanagement.repository;

import com.ticket.movieticketbookingmanagement.model.User;

import java.sql.SQLException;

public interface UserRepository {
    User signInUser(String username, String password);

    void addUser(User user) throws SQLException;

    boolean isUserExist(String username) throws SQLException;

    User getUserById(int id);

    User getUserByEmail(String email);

    void updateUser(User user);

    void deleteUser(int id);
}
