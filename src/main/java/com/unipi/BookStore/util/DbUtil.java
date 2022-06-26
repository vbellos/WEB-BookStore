package com.unipi.BookStore.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection != null)
            return connection;
        else {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?Encoding=true&characterEncoding=UTF-8", "root", "admin");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return connection;

        }
    }
}
