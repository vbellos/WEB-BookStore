<%--
  Created by IntelliJ IDEA.
  User: Vaggelis
  Date: 19-May-21
  Time: 12:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% //In case, if Author session is not set, redirect to Login page
    if((request.getSession(false).getAttribute("Author")== null) )
    {
%>
<jsp:forward page="/Login"></jsp:forward>
<%} %>
<%@include file="../WEB-INF/scripts.jsp"%>
<head>
    <title>Author</title>

</head>
<body>
<%@include file="navbar.jsp"%>

<div class="container-fluid p-3 my-3 ">
    <%@ include file="../au_booklist.jsp" %>
</div>

</body>
</html>
