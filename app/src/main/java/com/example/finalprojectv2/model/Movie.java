package com.example.finalprojectv2.model;


import java.io.Serializable;

public class Movie implements Serializable {
    private int movieId;
    private String name;
    private Double rating;
    private String description;
    private String movieType;
    private int viewed; //SQLite does not have a separate Boolean storage class. Instead, Boolean values are stored as integers 0 (false) and 1 (true).
    private String imagePath;
    private int favorite;

    //constructors
    public Movie() {
    }

    public Movie(int MovieId, String name, Double rating, String description,String movieType, int viewed, String imagePath, int favorite) {
        this.movieId = MovieId;
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.movieType = movieType;
        this.viewed = viewed;
        this.imagePath = imagePath;
        this.favorite = favorite;
    }

    public Movie(String name, Double rating, String description, String movieType, int viewed, String imagePath, int favorite) {
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.movieType = movieType;
        this.viewed = viewed;
        this.imagePath = imagePath;
        this.favorite = favorite;
    }

    //Getters and Setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
