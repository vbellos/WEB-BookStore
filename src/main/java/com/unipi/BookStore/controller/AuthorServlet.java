package com.unipi.BookStore.controller;

import com.unipi.BookStore.dao.BookDao;
import com.unipi.BookStore.model.Author;
import com.unipi.BookStore.model.Book;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "AuthorServlet", value = "/Author")
public class AuthorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author author = (Author) request.getSession().getAttribute("Author");
        System.out.println(request.getParameter("add"));
        System.out.println(request.getParameter("title"));

        if(author != null) {
            String add = request.getParameter("add");
            if (add != null) {
                AddBook(request, response);
            }
        }
        else{response.sendRedirect("./Login");}

    }

    void AddBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Book book = new Book();
        book.setAuthor(((Author) request.getSession().getAttribute("Author")).getUsername());
        book.setName(request.getParameter("title"));
        book.setGenre(request.getParameter("genre"));
        book.setPrice(Float.parseFloat(request.getParameter("price")));
        book.setDescription(request.getParameter("desc"));
        book.gen_id();
        String image = uploadimage(request,response);

        book.setImage(image);
        BookDao bookDao = new BookDao();
        bookDao.AddBook(book);
        response.sendRedirect("./Home");
    }




    String uploadimage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        File file;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);;
        String filePath = getServletContext().getInitParameter("file-upload"); ;
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter( );

        if( !isMultipart ) {

        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            while ( i.hasNext () ) {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () ) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    // Write the file
                    if( fileName.lastIndexOf("\\") >= 0 ) {
                        file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                    } else {
                        file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                    }
                    fi.write( file ) ;
                    out.println("Uploaded Filename: " + fileName + "<br>");
                    return fileName;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
        }
}
