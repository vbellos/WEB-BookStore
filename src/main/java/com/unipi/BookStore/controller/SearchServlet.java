package com.unipi.BookStore.controller;

import com.unipi.BookStore.dao.BookDao;
import com.unipi.BookStore.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchServlet", value = "/Search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author = request.getParameter("Author");
        String genre = request.getParameter("Genre");
        String key = request.getParameter("key");
        List<Book> bookList = new ArrayList<Book>();
        BookDao bookDao = new BookDao();

        if (author!= null && !author.equalsIgnoreCase(""))
        {
            bookList = bookDao.searchAuthor(author);
        }
        else if(genre != null && !genre.equalsIgnoreCase(""))
        {
            bookList = bookDao.searchGenre(genre);
        }
        else  if(key != null && !key.equalsIgnoreCase(""))
        {
            bookList = bookDao.search(key);
        }
        HttpSession session = request.getSession();
        session.setAttribute("booklist",bookList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Home.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
