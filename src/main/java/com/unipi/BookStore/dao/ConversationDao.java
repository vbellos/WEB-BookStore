package com.unipi.BookStore.dao;

import com.unipi.BookStore.model.Conversation;
import com.unipi.BookStore.model.Message;
import com.unipi.BookStore.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConversationDao {
    Connection connection = DbUtil.getConnection();


    public List<String> getConvUsers(String username)
    {
        List<String> users = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select receiver from messages where sender=? ");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                String user = rs.getString("receiver");
                if(!users.contains(user)){users.add(user);}
            }
            preparedStatement = connection.prepareStatement("select sender from messages where receiver=? ");
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                String user = rs.getString("sender");
                if(!users.contains(user)){users.add(user);}
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if(users.isEmpty()){return null;}
        return  users;
    }
    public Conversation get_conv (String username, String user)
    {
           Conversation conversation = new Conversation();
           List<Message> messages = new ArrayList<Message>();
           conversation.setMe(username);
           conversation.setUser(user);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from messages where (sender=? AND receiver=?) OR (sender=? AND receiver=?) ");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, user);
            preparedStatement.setString(3, user);
            preparedStatement.setString(4, username);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(preparedStatement);
            while (rs.next())
            {
                Message message = new Message();
                message.setReceiver(rs.getString("receiver"));
                message.setSender(rs.getString("sender"));
                message.setText(rs.getString("text"));
                message.setTime(rs.getTimestamp("time"));
                messages.add(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        conversation.setMessageList(messages);
        return conversation;
    }


}
