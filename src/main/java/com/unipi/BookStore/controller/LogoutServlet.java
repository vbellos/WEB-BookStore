package com.unipi.BookStore.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/Logout")
public class LogoutServlet extends HttpServlet {

    public LogoutServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); //Fetch session object

        if(session!=null) //If session is not null
        {
            session.invalidate(); //removes all session attributes bound to the session
            request.setAttribute("errMessage", "You have logged out successfully");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Home");
            requestDispatcher.forward(request, response);
            System.out.println("Logged out");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
