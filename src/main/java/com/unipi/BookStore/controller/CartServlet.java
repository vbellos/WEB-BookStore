package com.unipi.BookStore.controller;

import com.unipi.BookStore.dao.BookDao;
import com.unipi.BookStore.dao.TransDao;
import com.unipi.BookStore.model.Book;
import com.unipi.BookStore.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/Cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String removeButtonVal = request.getParameter("remove");
        String add = request.getParameter("add");
        String updateButtonVal = request.getParameter("update");
        String quantity = request.getParameter("quantity");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        String url = request.getHeader("referer");
        String buy = (String) request.getParameter("buy");

        if (id!=null && id!="")
        {
                if (add!=null) {
                    cart.addBook(getBook(id));
                    request.getSession().setAttribute("cart",cart);
                    response.sendRedirect(request.getHeader("referer"));
                } else if (removeButtonVal != null) {
                    cart.removeBook(getBook(id));
                    request.getSession().setAttribute("cart",cart);
                    request.getRequestDispatcher("/cart.jsp").forward(request, response);
                } else if (updateButtonVal != null) {
                    cart.updateQuantity(getBook(id),Integer.parseInt(quantity));
                    request.getSession().setAttribute("cart",cart);
                    request.getRequestDispatcher("/cart.jsp").forward(request, response);
                }

            }
        else if(buy != null)
        {
            String username = (String) request.getSession().getAttribute("username");
            if(username!= null)
            {
                if (!cart.getBookList().isEmpty()) {
                    TransDao transDao = new TransDao();
                    transDao.new_trans(username, cart);
                    request.getSession().setAttribute("cart", null);
                    response.sendRedirect("./Home");
                }
                else{response.sendRedirect("./Cart");}
            }
            else{response.sendRedirect("./Login");}
        }
        else {request.getRequestDispatcher("/cart.jsp").forward(request, response);}

    }

    public Book getBook(String id)
    {
        BookDao bookDao = new BookDao();
        int book_id = Integer.parseInt(id);
        Book book = bookDao.getBook(book_id);
        return book;
    }
}
