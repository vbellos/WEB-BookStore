<%@ page import="com.unipi.BookStore.model.Message" %>
<%@ page import="com.unipi.BookStore.model.Conversation" %>
<%@ page import="com.unipi.BookStore.dao.UserDao" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messages</title>
    <%@include file="WEB-INF/scripts.jsp"%>
    <link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" rel="stylesheet">
    <link href="./CSS/chatbox.css" rel="stylesheet">
</head>
<body>
<%if((session.getAttribute("Author"))!=null) { %>
<jsp:include page='Author/navbar.jsp'></jsp:include>
<%}else{%><jsp:include page='navbar.jsp'></jsp:include><%}%>

<%UserDao userDao = new UserDao();
  String user =  (String) request.getAttribute("user");
%>
<div class="container">
    <div class="row h-100">

        <div class="col-12">



            <link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" rel="stylesheet">
            <link href="/BookStore_war_exploded/CSS/chatbox.css" rel="stylesheet">



            <div class="page-content page-container" id="page-content">
                <div class="padding">
                    <div class="row container d-flex justify-content-center">
                        <div class="col-4">


                            <div class="card card-bordered">

                                <div class="card-header">
                                    <h4 class="card-title"><strong>Contacts</strong></h4>
                                </div><div class="ps-container ps-theme-default ps-active-y" id="chat-content" style="overflow-y: scroll !important; height:465px !important;">


                                <% List<String> Users = (List<String>) request.getAttribute("users"); %>
                                <% if (Users!=null){for (String u : Users){ %>

                                <div class="media media-chat centered">
                                    <form action="./Messages">
                                        <%String c = "btn btn-outline-success";
                                        if( user!=null){if(user.equalsIgnoreCase(u)){ c = "btn btn-success";}}
                                        %>
                                        <button class="<%=c%>" type="submit" name="user" id="user" value="<%=u%>">
                                            <i class="bi bi-person-circle"></i> <%=userDao.getUserFullname(u)%> </button></form>
                                </div>
                                        <%  }} %>

                                <div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;">
                                <div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div>
                            </div>
                                <div class="ps-scrollbar-y-rail" style="top: 0px; height: 0px; right: 2px;">
                                    <div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 2px;"></div>
                                </div>
                            </div></div>
                        </div>
                        <div class="col-md-8">
                        <div class="card card-bordered">
                            <div class="card-header">
                                <%
                                    String title = "No user selected"; if (user != null){title = userDao.getUserFullname(user);}
                                %>
                                <h4 class="card-title"><strong><i class="bi bi-person-circle"></i> <%=title%></strong></h4>
                            </div>
                            <div class="ps-container ps-theme-default ps-active-y" id="chat-content" style="overflow-y: scroll !important; height:400px !important;">

                                <%
                                    if(user!= null){
                                        Conversation conv = (Conversation) request.getAttribute("conv");
                                        if(conv!=null){
                                            for(Message m : conv.getMessageList()){
                                                String m_class;
                                                if (m.getSender().equalsIgnoreCase((String) session.getAttribute("username")))
                                                {m_class="media media-chat media-chat-reverse";} else{m_class="media media-chat" ;}
                                %>

                                <div class="<%=m_class%>">
                                    <div class="media-body">
                                        <p><%=m.getText()%></p>
                                        <p class="meta"><time datetime="<%=m.getTime()%>"><%=m.getTime()%></time></p>
                                    </div>
                                </div>

                                <%}}}%>




                                <div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;">
                                    <div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div>
                                </div>
                                <div class="ps-scrollbar-y-rail" style="top: 0px; height: 0px; right: 2px;">
                                    <div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 2px;"></div>
                                </div>
                            </div>

                            <%if(user != null) {%>
                            <form action = "./Messages">
                                <div class="publisher bt-1 border-light">
                                    <input type="hidden" name="user" value="<%=user%>">
                                    <input class="publisher-input" type="text" name = "message" placeholder="Write something">
                                    <button class="publisher-btn text-info" type = "submit" data-abc="true"><i class="fa fa-paper-plane"></i></button>

                                </div>
                            </form>
                            <% } %>

                        </div>
                    </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>



</body>
</html>
