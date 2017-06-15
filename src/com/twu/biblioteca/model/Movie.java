package com.twu.biblioteca.model;

public class Movie extends LibraryThing{
    private String director;
    private double rating;

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
        super(movieId, movieName, year);
        this.director = director;
        this.rating = rating;
    }

    public Movie() {}
}
