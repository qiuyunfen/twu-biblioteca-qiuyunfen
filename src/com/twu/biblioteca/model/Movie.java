package com.twu.biblioteca.model;

public class Movie {
    private int movieId;
    private String movieName;
    private int year;
    private String director;
    private double rating;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Movie(int movieId, String movieName,int year, String director, double rating) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public Movie() {}
}
