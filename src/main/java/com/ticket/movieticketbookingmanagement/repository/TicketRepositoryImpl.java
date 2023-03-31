package com.ticket.movieticketbookingmanagement.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class TicketRepositoryImpl implements TicketRepository{


    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;
    private int soldTicket;
    private double totalIncomeToday;
    private int totalMovies;

    public TicketRepositoryImpl (Connection connection) {
        this.connection = connection;
    }


    @Override
    public Integer totalAvailableMovies() {

        String query = "SELECT COUNT(id) FROM movie WHERE current = 'Showing'";

        try {

            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();

            if (result.next()) {

                totalMovies = result.getInt("count(id)");
            }

        } catch (Exception e) {e.printStackTrace();}
        return totalMovies;
    }



    @Override
    public Double totalIncomeToday() {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String query = "SELECT SUM(total) FROM customer WHERE date = '" + String.valueOf(sqlDate) + "'";

        try {

            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();

            if (result.next()) {
                totalIncomeToday = result.getDouble("SUM(total)");
            }

        } catch (Exception e) {e.printStackTrace();}
        return totalIncomeToday;
    }



    @Override
    public Integer countTicket() {

        String query = "SELECT count(id) FROM customer";

        try {

            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();

            if (result.next()) {
                soldTicket = result.getInt("count(id)");
            }

        } catch (Exception e) {e.printStackTrace();}
        return soldTicket;
    }

}
