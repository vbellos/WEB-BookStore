package com.unipi.BookStore.dao;

import com.unipi.BookStore.model.Author;
import com.unipi.BookStore.model.User;
import com.unipi.BookStore.util.DbUtil;

import java.sql.*;
import java.util.*;

public class AuthorDao {

    UserDao userDao;
    Connection connection = null;

    public AuthorDao()
    {
        connection = DbUtil.getConnection();
        userDao = new UserDao();
    }

    public List<Author> getAllAuthors()
    {
        List<Author> authorList = new ArrayList<Author>() ;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from authors");

            while (rs.next()) {
                Author author = new Author();
                author.setUsername(rs.getString("username"));
                author.setPen_name("pen_name");
                User user = userDao.getUser(author.getUsername());
                author.inherit(user);
                authorList.add(author);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return authorList;
    }

    public Author getAuthor(String username)
    {
        Author author = new Author();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("select * from authors where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                author.setUsername(rs.getString("username"));
                author.setPen_name("pen_name");
                User user = userDao.getUser(author.getUsername());
                author.inherit(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return author;
    }
}
