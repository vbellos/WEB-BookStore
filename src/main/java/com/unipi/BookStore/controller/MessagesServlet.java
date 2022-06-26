package com.unipi.BookStore.controller;

import com.unipi.BookStore.dao.ConversationDao;
import com.unipi.BookStore.dao.MessageDao;
import com.unipi.BookStore.model.Conversation;
import com.unipi.BookStore.model.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "MessagesServlet", value = "/Messages")
public class MessagesServlet extends HttpServlet {
    ConversationDao conversationDao = new ConversationDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String me = (String) session.getAttribute("username");
        String user = (String) request.getParameter("user");
        String message = (String) request.getParameter("message");
        boolean m_sent = false;
        if(me!=null) {
            System.out.println(me);
            List<String> users = conversationDao.getConvUsers(me);
            if (users != null){request.setAttribute("users",users);}
            if(user != null)
            {
                System.out.println(user);
                Conversation conv = conversationDao.get_conv(me,user);
                request.setAttribute("user",user);
                request.setAttribute("conv",conv);

                if(message!= null)
                {
                    newMessage(me,user,message);
                    response.sendRedirect(request.getHeader("referer"));
                    m_sent = true;
                }

            }
            if(!m_sent) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/messages.jsp");
                dispatcher.forward(request, response);
            }
        }
        else
        {
            response.sendRedirect("./Login");
        }


    }
    public void newMessage(String me,String user,String text)
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        MessagesServlet messagesServlet = new MessagesServlet();
        MessageDao messageDao = new MessageDao();
        Message message = new Message();
        message.setSender(me);
        message.setReceiver(user);
        message.setText(text);
        message.setTime(timestamp);
        messageDao.Add_Message(message);
    }
}
