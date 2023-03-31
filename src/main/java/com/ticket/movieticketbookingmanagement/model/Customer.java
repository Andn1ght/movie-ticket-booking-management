package com.ticket.movieticketbookingmanagement.model;

import java.sql.Time;
import java.util.Date;

public class Customer {

    private Integer id;
    private String type;
    private String title;
    private Integer quantity;
    private double total;
    private Date date;
    private Time time;

    public Customer(Integer id, String type, String title, Integer quantity, double total, Date date, Time time) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }
    public Time getTime() {
        return time;
    }
    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }
}
