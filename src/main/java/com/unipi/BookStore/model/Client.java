package com.unipi.BookStore.model;

public class Client extends User{
    public void inherit(User user)
    {
        this.setUsername(user.getUsername());
        this.setFirstname(user.getFirstname());
        this.setLastname(user.getLastname());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setPhone(user.getPhone());
    }
}
