package com.unipi.BookStore.controller;

import com.unipi.BookStore.dao.UserDao;
import com.unipi.BookStore.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountServlet", value = "/Account")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        User user = userDao.getUser((String) request.getSession().getAttribute("username"));
        if (user == null){response.sendRedirect("./Login");}
        else {
            response.getWriter().append("Served at: ").append(request.getContextPath());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/account.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        UserDao userDao = new UserDao();
        User user = userDao.getUser(username);
        if(user != null)
        {
            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);
            userDao.updateUser(user);
            response.sendRedirect("./Account");
        }else {
            response.sendRedirect("./Login");
        }
    }
}
