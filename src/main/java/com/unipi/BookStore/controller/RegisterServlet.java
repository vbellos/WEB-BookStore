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

@WebServlet(name = "RegisterServlet", value = "/Register")
public class RegisterServlet extends HttpServlet {

    private UserDao userdao;

    public void init() {
        userdao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        RequestDispatcher dispatcher =request.getRequestDispatcher("/WEB-INF/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String role = request.getParameter("regas");

        User user = new User();
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setType(role);

        int result = userdao.RegisterUser(user);
        String returnPage = "/WEB-INF/register.jsp";
        if (result == 1)
        {
            returnPage = "/WEB-INF/register_success.jsp";
        }

        RequestDispatcher dispatcher =request.getRequestDispatcher(returnPage);
        dispatcher.forward(request, response);
    }
}
