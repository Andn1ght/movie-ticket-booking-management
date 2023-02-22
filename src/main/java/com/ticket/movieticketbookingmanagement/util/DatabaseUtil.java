package com.ticket.movieticketbookingmanagement.util;

import java.sql.*;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/movie_ticket_booking_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }

    public static synchronized void createTables() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        String createUsersTableQuery = "CREATE TABLE IF NOT EXISTS user ("
                + "id INT NOT NULL AUTO_INCREMENT,"
                + "username VARCHAR(100) NOT NULL UNIQUE,"
                + "email VARCHAR(100) NOT NULL UNIQUE,"
                + "password VARCHAR(50) NOT NULL,"
                + "PRIMARY KEY (id)"
                + ")";

        String createMoviesTableQuery = "CREATE TABLE IF NOT EXISTS movie ("
                + "id INT NOT NULL AUTO_INCREMENT,"
                + "movieTitle VARCHAR(100) NOT NULL,"
                + "genre VARCHAR(100) NOT NULL,"
                + "duration VARCHAR(100) NOT NULL,"
                + "image VARCHAR(500) NOT NULL,"
                + "date DATE,"
                + "current VARCHAR(100),"
                + "PRIMARY KEY (id)"
                + ")";

        // Create the users and movies tables
        statement.execute(createUsersTableQuery);
        statement.execute(createMoviesTableQuery);

        closeConnection(connection, statement, null);
    }

    public static boolean tableExists(String tableName) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = ? AND table_name = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, "movie_ticket_booking_system");
            statement.setString(2, tableName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 1;
            }
            return false;
        } finally {
            closeConnection(connection, statement, resultSet);
        }
    }
}

