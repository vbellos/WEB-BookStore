package com.unipi.BookStore.controller;

import com.unipi.BookStore.dao.BookDao;
import com.unipi.BookStore.model.Author;
import com.unipi.BookStore.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AuBookServlet", value = "/AuBook")
public class AuBookServlet extends HttpServlet {
    BookDao bookDao;
    public AuBookServlet()
    {
        bookDao = new BookDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        Book book = bookDao.getBook(Integer.parseInt(id));

        Author author = (Author) request.getSession().getAttribute("Author");

            if (book != null) {
                if (author != null && author.getUsername().equalsIgnoreCase(book.getAuthor())) {
                    request.setAttribute("Book", book);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/au_bookview.jsp");
                    dispatcher.forward(request, response);
                }
                else{response.sendRedirect("./Home");}
            } else {
                response.sendRedirect("./Home");
            }

    }

}
