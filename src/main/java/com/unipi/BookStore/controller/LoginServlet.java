package com.unipi.BookStore.controller;

import com.unipi.BookStore.dao.LoginDao;
import com.unipi.BookStore.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {

    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        RequestDispatcher dispatcher =request.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = loginDao.validate(username,password);
            if (user != null)
            {
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(10*60);
                session.setAttribute("username", user.getUsername());
                session.setAttribute("type", user.getType());
                session.setAttribute("booklist",null);
                response.sendRedirect("./Home");
            }
            else
            {
                HttpSession session = request.getSession();
                RequestDispatcher dispatcher =request.getRequestDispatcher("/WEB-INF/login.jsp");
                dispatcher.forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }
}
