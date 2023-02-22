package com.ticket.movieticketbookingmanagement.model;

import java.time.LocalDate;
import java.util.Date;

public class Movie {

    private Integer id;
    private String title;
    private String genre;
    private String duration;
    private String image;
    private LocalDate date;
    private String current;

    public Movie(Integer id, String title, String genre, String duration, String image, LocalDate date, String current) {

        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.image = image;
        this.date = date;
        this.current = current;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDuration() {
        return duration;
    }

    public String getImage() {
        return image;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCurrent() {
        return current;
    }
}

