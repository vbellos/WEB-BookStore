package com.unipi.BookStore.controller;

import com.unipi.BookStore.dao.BookDao;
import com.unipi.BookStore.model.Author;
import com.unipi.BookStore.model.Book;
import com.unipi.BookStore.util.IdGenerator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "EditBookServlet", value = "/EditBook")
public class EditBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if((Author)request.getSession().getAttribute("Author")!=null)
        {
            if(isMultipart){InitMultiPart(request,response);}
            else{InitPage(request,response);}
        }else {response.sendRedirect("./Login");}

    }

    public void InitPage(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        String id = request.getParameter("id");
        BookDao bookDao = new BookDao();
        Book book = bookDao.getBook(Integer.parseInt(id));
        if(book != null) {
            RequestDispatcher dispatcher =request.getRequestDispatcher("/editbook.jsp");
            request.setAttribute("Book", book);
            if (request.getParameter("edit") != null) {
                editBook(request, response, book);
            }
            else{dispatcher.forward(request,response);}
        }else{response.sendRedirect("./Home");}
    }
    public void editBook(HttpServletRequest request,HttpServletResponse response,Book book) throws ServletException,IOException
    {
        book.setName(request.getParameter("title"));
        book.setGenre(request.getParameter("genre"));
        book.setPrice(Float.parseFloat(request.getParameter("price")));
        book.setDescription(request.getParameter("desc"));
        BookDao bookDao = new BookDao();
        bookDao.updateBook(book);
        response.sendRedirect("./Home");

    }

    public void InitMultiPart(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        HashMap<String, String> map = new HashMap<String, String>();
        ServletContext context = this.getServletConfig().getServletContext();
        String filePath = context.getInitParameter("file-upload");
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        try{
            // Parse the request to get file items.
            List<FileItem> items = upload.parseRequest(request);
            // Process the uploaded file items
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext ()){
                FileItem fi = iter.next();
                if ( fi.isFormField () ) {
                    // Process a regular form field
                    String otherFieldName = fi.getFieldName();
                    String otherFieldValue = fi.getString();
                    map.put(otherFieldName, otherFieldValue);
                } else {
                    // Get the uploaded file parameters
                    String fileName = fi.getName();
                    String fieldName = fi.getFieldName();
                    long sizeInBytes = fi.getSize();
                    if(fi.getName() == ""){
                        continue;
                    }
                    // Write the file
                    String path = getServletContext().getInitParameter("file-upload");
                    String filename = new IdGenerator().IntID() +".jpg";
                    File file = new File(path + File.separator + filename) ;

                    fi.write( file ) ;
                    if(!fileName.equals("")){
                        System.out.println("fileName: " + filename);
                    }
                    map.put("ImageFile", filename);
                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        if (!map.isEmpty()) {
            BookDao bookDao = new BookDao();
            int id = Integer.parseInt(map.get("id"));
            Book book = bookDao.getBook(id);
            if (map.containsKey("ImageFile")) {
                book.setImage(map.get("ImageFile"));
            }
            bookDao.updateBook(book);
            response.sendRedirect("./Home");
        }

    }
}
