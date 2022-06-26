package com.unipi.BookStore.model;

import java.util.List;

public class Conversation {
    String me,user;
    List<Message> messageList;

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
