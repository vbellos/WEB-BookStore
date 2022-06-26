package com.unipi.BookStore.controller;

import com.unipi.BookStore.dao.BookDao;
import com.unipi.BookStore.model.Book;
import com.unipi.BookStore.model.Review;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "BookServlet", value = "/Book")
public class BookServlet extends HttpServlet {
    BookDao bookDao;
    public BookServlet()
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
        System.out.println(id + "b");
        if (book != null) {

            HttpSession session = request.getSession();
            request.setAttribute("Book",book);
            String review = request.getParameter("review");

            if(review!=null)
            {
                AddReview(request,response,book);
            }
            else{
                RequestDispatcher dispatcher =request.getRequestDispatcher("/bookview.jsp");
                dispatcher.forward(request, response);
            }
        }
        else
        {
            response.sendRedirect("./Home");
        }
    }
    public void AddReview(HttpServletRequest request ,HttpServletResponse response,Book book) throws ServletException , IOException
    {
        int rating = Integer.parseInt(request.getParameter("rating"));
        if (request.getSession().getAttribute("username")!=null)
        {
            Review review = new Review();
            review.setBook_id(book.getId());
            review.setUsername((String) request.getSession().getAttribute("username"));
            review.setComment(request.getParameter("review"));
            review.setRating(rating);
            bookDao.addReview(review);
            response.sendRedirect(request.getHeader("referer"));
        }
        else{response.sendRedirect("./Login");}
    }


}
