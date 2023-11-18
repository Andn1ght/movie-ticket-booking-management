package com.ticket.movieticketbookingmanagement.repository;

import com.ticket.movieticketbookingmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;
    private ResultSet result;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User signInUser(String username, String password){
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, username);
            statement.setString(2, password);
            result = statement.executeQuery();

            if (result.next()) {
                String email = result.getString("email");
                return new User(username, email, password);
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO user (username, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public boolean isUserExist(String username) throws SQLException {
        String query = "SELECT username FROM user WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }


    @Override
    public User getUserById(int id) {
        String query = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String username = result.getString("username");
                String email = result.getString("email");
                String password = result.getString("password");
                return new User(username, email, password);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String username = result.getString("username");
                String password = result.getString("password");
                return new User(username, email, password);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(User user) {
        String query = "UPDATE user SET username = ?, email = ?, password = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
