package com.unipi.BookStore.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    float total;
    List<Book> bookList = new ArrayList<Book>();

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void addBook(Book book)
    {
        boolean c = false;
        for(Book b :bookList)
        {
            if (b.getId() == book.getId())
            { b.setQuantity(b.quantity+1);c=true;break;}
        }
        if(!c){
            book.setQuantity(1);
            bookList.add(book);}
        updateTotal();
    }
    public void removeBook(Book book)
    {
        for (Book b: bookList)
        {
            if (b.getId() == book.getId())
            {bookList.remove(b);break;}
        }
        updateTotal();
    }
    public void updateQuantity(Book book , int quantity)
    {
        for (Book b: bookList)
        {
            if (b.getId() == book.getId())
            {b.setQuantity(quantity);break;}
        }
        updateTotal();
    }


    public void updateTotal()
    {
        float t = 0;
        for(Book book:bookList)
        {
            t+= book.getPrice()* book.getQuantity();
        }
        this.total = t;
    }

}
