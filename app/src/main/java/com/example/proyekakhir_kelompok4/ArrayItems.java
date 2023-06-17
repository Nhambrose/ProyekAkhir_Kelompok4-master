package com.example.proyekakhir_kelompok4;

public class ArrayItems {
    private int movie_image;
    private int movie_rating;
    private String movie_title;
    private String movie_desc;
    private String movie_price;

    public ArrayItems(int movieImage, int movieRating, String movieTitle, String movieDesc, String moviePrice)
    {
        movie_image = movieImage;
        movie_rating = movieRating;
        movie_title = movieTitle;
        movie_desc = movieDesc;
        movie_price = moviePrice;
    }

    public int getMovie_image() {
        return movie_image;
    }

    public int getMovie_rating() {
        return movie_rating;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public String getMovie_desc() {
        return movie_desc;
    }

    public String getMovie_price() {
        return movie_price;
    }
}
