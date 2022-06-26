package com.unipi.BookStore.dao;

import com.unipi.BookStore.model.User;
import com.unipi.BookStore.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    private Connection connection;

    public User validate(String username,String password) throws ClassNotFoundException, SQLException {
        User user = new User();

        connection = DbUtil.getConnection();

        //Create a statement using connection object
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ? ");
        {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {


                user.setType(rs.getString("type"));
                user.setFirstname(rs.getString("fname"));
                user.setLastname(rs.getString("lname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
            }
            else user = null;
        }
        return user;
    }

}
