package com.katana.quikread.models;

import java.io.Serializable;

/**
 * This is the model formed after the book has been found on goodreads. Contains relevant data
 * from both quikr and goodreads api
 *
 * @author Akshay
 * @version 1.0.0
 * @since 13-Sep-15
 */
public class QuikreadItem implements Serializable{

    //Quikr params
    private String genre;

    private String price;

    //goodreads
    private String title;

    private String imageUrl;

    private String description;

    private String author;

    private String rating;

    public QuikreadItem(String genre, String price, String title, String imageUrl, String description, String author, String rating) {
        this.genre = genre;
        this.price = price;
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.author = author;
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public String getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getRating() {
        return rating;
    }
}
