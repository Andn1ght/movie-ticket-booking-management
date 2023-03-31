package com.ticket.movieticketbookingmanagement.repository;

public interface TicketRepository {

    Integer totalAvailableMovies();

    Double totalIncomeToday();

    Integer countTicket();
}
