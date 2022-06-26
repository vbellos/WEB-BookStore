package com.unipi.BookStore.model;

import com.unipi.BookStore.dao.BookDao;
import com.unipi.BookStore.util.IdGenerator;

import java.util.List;

public class Book {
    int id,sold,quantity;
    String name,author,description,genre,image;
    float price,rating;

    public Book()
    {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getRating() {
        BookDao bookDao = new BookDao();

        return bookDao.getCurrentRate(this);
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public List<Review> get_reviews()
    {
        BookDao bookDao = new BookDao();
        return bookDao.getbookReviews(this.getId());
    }
    public void gen_id()
    {
        this.id = new IdGenerator().IntID();
    }

}
