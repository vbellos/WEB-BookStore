package com.unipi.BookStore.model;

import com.unipi.BookStore.dao.TransDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sales {
    Author author;
    TransDao transDao;


    public Sales(Author a)
    {
        this.author = a;
        transDao = new TransDao();
    }
    public List<Integer>getSoldBooks()
    {
        return transDao.getAuthorBooks(author.getUsername());
    }

    public List<BookTrans> getBookTrans(int id)
    {
        return  transDao.getBookAllTrans(id);
    }
    public float getBookEarnings(int id)
    {
        List<BookTrans> bookTrans = transDao.getBookAllTrans(id);
        float s = 0;
        for (BookTrans b : bookTrans){s += b.getTotal();}
        return s;
    }

    public int getTotalQuantity()
    {
        List<BookTrans> bookTrans = transDao.getAuthorAllTrans(author.getUsername());
        int s = 0;
        for (BookTrans b : bookTrans){s += b.getQuantity();}
        return s;
    }

    public float getTotalEarnings()
    {
        List<BookTrans> bookTrans = transDao.getAuthorAllTrans(author.getUsername());
        float s = 0;
        for (BookTrans b : bookTrans){s += b.getTotal();}
        return s;
    }

    public List<BookTrans> getAllSales()
    {
        List<BookTrans> bookTrans = transDao.getAuthorAllTrans(author.getUsername());
        Collections.reverse(bookTrans);
        return bookTrans;
    }
    public List<SalesItem> getItems()
    {
        List<SalesItem> salesItems = new ArrayList<SalesItem>();
        List<Integer> books = getSoldBooks();
        for (int id:books) {
            SalesItem item = new SalesItem();
            item.setBook_id(id);
            item.setBookTrans(getBookTrans(id));
            salesItems.add(item);
        }
        return salesItems;
    }

}
