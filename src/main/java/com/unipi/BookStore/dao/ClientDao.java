package com.unipi.BookStore.dao;

import com.unipi.BookStore.model.Client;
import com.unipi.BookStore.model.User;
import com.unipi.BookStore.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    UserDao userDao;
    Connection connection = null;

    public ClientDao()
    {
        connection = DbUtil.getConnection();
        userDao = new UserDao();
    }

    public List<Client> getAllClients()
    {
        List<Client> clientList = new ArrayList<Client>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from clients");
            while (rs.next())
            {
                Client client = new Client();
                String username = rs.getString("username");
                User user=userDao.getUser(username);
                client.inherit(user);
                clientList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }
    public Client getClient(String username)
    {
        Client client = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("select * from clients where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next())
            {
                client = new Client();
                User user = userDao.getUser(username);
                client.inherit(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }

}
