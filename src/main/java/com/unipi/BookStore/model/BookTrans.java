package com.unipi.BookStore.model;

import java.sql.Timestamp;

public class BookTrans {
    Timestamp DateTime;
    int book_id,quantity;
    float price,total;
    String author;

    public Timestamp getDateTime() {
        return DateTime;
    }

    public float getTotal()
    {
        return  price*quantity;
    }

    public void setDateTime(Timestamp dateTime) {
        DateTime = dateTime;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
