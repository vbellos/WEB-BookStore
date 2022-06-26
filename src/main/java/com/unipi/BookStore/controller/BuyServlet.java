package com.unipi.BookStore.controller;

import com.unipi.BookStore.dao.TransDao;
import com.unipi.BookStore.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BuyServlet", value = "/Buy")
public class BuyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(username!= null && cart !=null)
        {
            TransDao transDao = new TransDao();
            transDao.new_trans(username,cart);
            request.getSession().setAttribute("cart", null);
            response.sendRedirect("./Home");
        }
        else{response.sendRedirect("./Login");}
    }
}
