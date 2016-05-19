package com.app.getyourreviews.model;

import java.util.Date;

/**
 * Created by ouafaebenelkadi on 5/18/16.
 */
public class Review {

    private String title;
    private float rating;
    private String date;
    private String message;
    private String author;

    public Review(String title, float rating, String date, String message, String author) {
        this.title = title;
        this.rating = rating;
        this.date = date;
        this.message = message;
        this.author = author;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
