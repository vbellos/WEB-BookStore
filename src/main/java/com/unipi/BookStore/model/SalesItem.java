package com.unipi.BookStore.model;

import java.util.List;

public class SalesItem {

    int book_id;
    List<BookTrans> bookTrans;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getQuantity() {
        int q = 0;
        for (BookTrans b : bookTrans){q+= b.getQuantity();}
        return q;
    }



    public float getEarnigs() {
        float earnings = 0;
        for (BookTrans b : bookTrans){earnings+= b.getTotal();}
        return earnings;
    }

    public List<BookTrans> getBookTrans() {
        return bookTrans;
    }

    public void setBookTrans(List<BookTrans> bookTrans) {
        this.bookTrans = bookTrans;
    }
}
