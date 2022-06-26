package com.unipi.BookStore.dao;

import com.unipi.BookStore.model.User;
import com.unipi.BookStore.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection connection = null;

    public UserDao()
    {
        connection = DbUtil.getConnection();
    }

    public int RegisterUser(User user) {
        int result = 0;

        //Create a statement using connection object
        PreparedStatement preparedStatement;
        try {

            preparedStatement = connection.prepareStatement("INSERT INTO users (username,fname,lname,password,email,phone,type) VALUES (?, ?, ?, ?, ?,?,?);");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getType());


            System.out.println(preparedStatement);
            System.out.println(user.getType().trim());
            //Execute the query or update query
            result = preparedStatement.executeUpdate();

            if(user.getType().equalsIgnoreCase("Author") && result!= 0)
            {
                String p = user.getFirstname() + " " + user.getLastname();
                RegAuthor(user.getUsername(), p);
            }
            else if(result!= 0)
            {
                RegClient(user.getUsername());
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    public int RegAuthor(String username,String pen_name)
    {
        int result = 0;
        //Create a statement using connection object
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO authors (username,pen_name) VALUES (?, ?);");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pen_name);
            result = preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {e.printStackTrace();}
        return result;
    }
    public int RegClient(String username)
    {
        int result = 0;
        //Create a statement using connection object
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO clients (username) VALUES (?);");
            preparedStatement.setString(1, username);
            result = preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {e.printStackTrace();}
        return result;
    }


    public void updateUser(User User) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set  fname=?, lname=?,  password =?, email =?,type =?,phone=? where username=?");
            // Parameters start with 1

            preparedStatement.setString(1, User.getFirstname());
            preparedStatement.setString(2, User.getLastname());
            preparedStatement.setString(3, User.getPassword());
            preparedStatement.setString(4, User.getEmail());
            preparedStatement.setString(5, User.getType());
            preparedStatement.setString(6, User.getPhone());
            preparedStatement.setString(7, User.getUsername());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<User> getAllUsers() {
        List<User> Users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                User user = new User();
                user.setLastname(rs.getString("lname"));
                user.setFirstname(rs.getString("fname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setType(rs.getString("type"));

                Users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Users;
    }
    public User getUser(String username)
    {
        User user = new User();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from users where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setLastname(rs.getString("lname"));
                user.setFirstname(rs.getString("fname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setType(rs.getString("type"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String getUserFullname(String username)
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString("fname") + " " + rs.getString("lname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
