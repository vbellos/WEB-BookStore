package com.unipi.BookStore.controller;

import com.unipi.BookStore.dao.AuthorDao;
import com.unipi.BookStore.dao.BookDao;
import com.unipi.BookStore.dao.ClientDao;
import com.unipi.BookStore.dao.UserDao;
import com.unipi.BookStore.model.Author;
import com.unipi.BookStore.model.Book;
import com.unipi.BookStore.model.Cart;
import com.unipi.BookStore.model.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "HomeServlet", value = "/Home")
public class HomeServlet extends HttpServlet {
    UserDao userDao;
    ClientDao clientDao;
    BookDao bookDao;
    AuthorDao authorDao;
    public HomeServlet()
    {
        userDao = new UserDao();
        clientDao = new ClientDao();
        authorDao = new AuthorDao();
        bookDao = new BookDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String type = (String) session.getAttribute("type");
        if (username != null && !username.equalsIgnoreCase(""))
        {
            if(type.equalsIgnoreCase("Author"))
            {
                Author author = authorDao.getAuthor(username);
                session.setAttribute("Author",author);
                initAuthor(request,response ,author);
            }
            else
            {
                Client client = clientDao.getClient(username);
                session.setAttribute("Client",client);
                initClient(request,response);
            }
        }else {initClient(request,response);}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void initClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String all= request.getParameter("all");
        ArrayList<Book> booklist = (ArrayList<Book>) session.getAttribute("booklist");
        Cart cart = (Cart) session.getAttribute("cart");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Home.jsp");
        if(booklist == null || booklist.isEmpty() || all!=null)
        {
            session.setAttribute("booklist",bookDao.getAllBooks());
        }
        if(cart == null)
        {
            session.setAttribute("cart",new Cart());
        }
        dispatcher.forward(request,response);
    }

    public void initAuthor(HttpServletRequest request, HttpServletResponse response,Author author) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Author/Home.jsp");

        session.setAttribute("booklist",bookDao.searchAuthor(author.getUsername()));

        dispatcher.forward(request,response);
    }
}
