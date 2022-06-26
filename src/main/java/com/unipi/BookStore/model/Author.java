package com.unipi.BookStore.model;

import java.util.List;

public class Author extends User{
    String pen_name;
    List<Book> bookList;

    public void inherit(User user)
    {
        this.setUsername(user.getUsername());
        this.setFirstname(user.getFirstname());
        this.setLastname(user.getLastname());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setPhone(user.getPhone());
    }

    public String getPen_name() {
        return pen_name;
    }

    public void setPen_name(String pen_name) {
        this.pen_name = pen_name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
