package com.unipi.BookStore.dao;

import com.unipi.BookStore.model.Book;
import com.unipi.BookStore.model.BookTrans;
import com.unipi.BookStore.model.Cart;
import com.unipi.BookStore.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransDao {

    Connection connection = DbUtil.getConnection();

    public void new_trans(String username, Cart cart) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String trans_id = username + System.currentTimeMillis() + cart.getTotal();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO transactions(id,username,price,date) VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, trans_id);
            preparedStatement.setString(2, username);
            preparedStatement.setFloat(3, cart.getTotal());
            preparedStatement.setTimestamp(4, timestamp);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Book b : cart.getBookList()) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book_transaction(trans_id,book_id,price,quantity,author,datetime) VALUES (?, ?, ?, ?,?,?);");
                preparedStatement.setString(1, trans_id);
                preparedStatement.setInt(2, b.getId());
                preparedStatement.setFloat(3, b.getPrice());
                preparedStatement.setInt(4, b.getQuantity());
                preparedStatement.setString(5, b.getAuthor());
                preparedStatement.setTimestamp(6, timestamp);
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("UPDATE `bookstore`.`books` SET `sold`=? WHERE  `id`=? ;");
                preparedStatement.setInt(1, b.getSold() + b.getQuantity());
                preparedStatement.setInt(2, b.getId());
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public List<Integer> getAuthorBooks(String name)
    {
        List<Integer> books = new ArrayList<Integer>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT book_id FROM `bookstore`.`book_transaction` where author=?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                books.add(rs.getInt("book_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<BookTrans> getBookAllTrans(int id)
    {
        List<BookTrans> transList = new ArrayList<BookTrans>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `bookstore`.`book_transaction` where book_id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
               BookTrans bookTrans = new BookTrans();
               bookTrans.setBook_id(id);
               bookTrans.setAuthor(rs.getString("author"));
               bookTrans.setPrice(rs.getFloat("price"));
               bookTrans.setQuantity(rs.getInt("quantity"));
               bookTrans.setDateTime(rs.getTimestamp("datetime"));
               transList.add(bookTrans);
            }
            return transList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<BookTrans> getAuthorAllTrans(String name)
    {
        List<BookTrans> transList = new ArrayList<BookTrans>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `bookstore`.`book_transaction` where author=?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                BookTrans bookTrans = new BookTrans();
                bookTrans.setBook_id(rs.getInt("book_id"));
                bookTrans.setAuthor(rs.getString("author"));
                bookTrans.setPrice(rs.getFloat("price"));
                bookTrans.setQuantity(rs.getInt("quantity"));
                bookTrans.setDateTime(rs.getTimestamp("datetime"));
                transList.add(bookTrans);
            }
            return transList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
