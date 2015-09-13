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
public class QuikreadItem implements Serializable {

    //Quikr params
    private String genre;

    private String price;

    //goodreads
    private String title;

    private String imageUrl;

    private String description;

    private String author;

    private String rating;

    private String isbn;

    private String cityName;

    private String geoPin;

    private String quickrContent;

    public QuikreadItem(String author, String cityName, String description, String genre, String geoPin,
                        String imageUrl, String isbn, String price, String rating, String title,String quickrContent) {
        this.author = author;
        this.cityName = cityName;
        this.description = description;
        this.genre = genre;
        this.geoPin = geoPin;
        this.imageUrl = imageUrl;
        this.isbn = isbn;
        this.price = price;
        this.rating = rating;
        this.title = title;
        this.quickrContent = quickrContent;
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

    public String getIsbn() {
        return isbn;
    }

    public String getCityName() {
        return cityName;
    }

    public String getGeoPin() {
        return geoPin;
    }

    public String getQuickrContent() {
        return quickrContent;
    }
}
