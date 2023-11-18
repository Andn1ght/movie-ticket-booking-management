package com.ticket.movieticketbookingmanagement.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class TicketRepositoryImpl implements TicketRepository {

    private final Connection connection;

    public TicketRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer totalAvailableMovies() {
        String query = "SELECT COUNT(id) FROM movie WHERE current = 'Showing'";
        return executeCountQuery(query);
    }

    @Override
    public Double totalIncomeToday() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String query = "SELECT SUM(total) FROM customer WHERE date = ?";
        return executeSumQuery(query, String.valueOf(sqlDate));
    }

    @Override
    public Integer countTicket() {
        String query = "SELECT count(id) FROM customer";
        return executeCountQuery(query);
    }

    private Integer executeCountQuery(String query) {
        try (PreparedStatement prepare = connection.prepareStatement(query);
             ResultSet result = prepare.executeQuery()) {
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Double executeSumQuery(String query, String parameter) {
        try (PreparedStatement prepare = connection.prepareStatement(query)) {
            prepare.setString(1, parameter);
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    return result.getDouble(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
