<%@ page import="com.unipi.BookStore.dao.UserDao" %>
<%@ page import="com.unipi.BookStore.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
    <%@include file="WEB-INF/scripts.jsp"%>
</head>
<body>

<%if((session.getAttribute("Author"))!=null) { %>
<jsp:include page='Author/navbar.jsp'></jsp:include>
<%}else{%><jsp:include page='navbar.jsp'></jsp:include><%}%>

<%
    UserDao userDao = new UserDao();
    String uname = (String) session.getAttribute("username");
    User user = userDao.getUser(uname);
    if(user == null)
    {
        response.sendRedirect("./Login");
    }

%>
<br>
<br>

<div>

    <div class="container border rounded">
        <h3 class="text-center">My account</h3>
        <hr>
        <!-- <div class="container text-center"> -->
        <div class="container text-center">
            <form action="./Account" method="post" class="form-horizontal" content="text/html;charset=UTF-8" >
                <fieldset>


                    <div class="control-group">
                        <label class="control-label" for="fname">First Name:</label>
                        <div class="controls">
                            <input id="fname" name="fname" type="text" value="<%=user.getFirstname()%>"  class="input-xlarge" required="">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="control-group">
                        <label class="control-label" for="lname">Last Name:</label>
                        <div class="controls">
                            <input id="lname" name="lname" type="text" value="<%=user.getLastname()%> " class="input-xlarge" required="">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="control-group">
                        <label class="control-label" for="email">Email:</label>
                        <div class="controls">
                            <input id="email" name="email" type="text" value="<%=user.getEmail()%>" class="input-xlarge" required="">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="control-group">
                        <label class="control-label" for="phone">Phone:</label>
                        <div class="controls">
                            <input id="phone" name="phone" type="text" value="<%=user.getPhone()%>" class="input-xlarge" required="">

                        </div>
                    </div>

                    <!-- Password input-->
                    <div class="control-group">
                        <label class="control-label" for="password">Password:</label>
                        <div class="controls">
                            <input id="password" name="password" type="password" value="<%=user.getPassword()%>" class="input-xlarge" required="">

                        </div>
                    </div>

                    <br>
                    <!-- Button -->
                    <div class="control-group">
                        <input type="submit" value="Update Info" class="btn btn-success" />
                    </div>

                </fieldset>
            </form>
        </div>
    </div>
    </div>

</body>
</html>
