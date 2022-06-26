package com.unipi.BookStore.dao;

import com.unipi.BookStore.model.Message;
import com.unipi.BookStore.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    Connection connection = null;

    public MessageDao() {
        connection = DbUtil.getConnection();
    }

    public void Add_Message(Message message) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO messages (sender,receiver,text,time) VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, message.getSender());
            preparedStatement.setString(2, message.getReceiver());
            preparedStatement.setString(3,message.getText());
            preparedStatement.setTimestamp(4,message.getTime());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public List<Message> get_messages(String username){
        List<Message> messageList = new ArrayList<Message>();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from messages where sender=? OR receiver=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                Message message = new Message();
                message.setReceiver(rs.getString("receiver"));
                message.setSender(rs.getString("sender"));
                message.setText(rs.getString("text"));
                message.setTime(rs.getTimestamp("time"));
                messageList.add(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return messageList;
    }

    public List<Message> get_conv(String username, String user2){
        List<Message> messageList = new ArrayList<Message>();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from messages where sender=? OR receiver=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                Message message = new Message();
                message.setReceiver(rs.getString("receiver"));
                message.setSender(rs.getString("sender"));
                message.setText(rs.getString("text"));
                message.setTime(rs.getTimestamp("time"));
                messageList.add(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return messageList;
    }
}
