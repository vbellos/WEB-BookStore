<%@ page import="com.unipi.BookStore.model.Author" %>
<%@ page import="com.unipi.BookStore.model.Sales" %>
<%@ page import="com.unipi.BookStore.model.SalesItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<% //In case, if Author session is not set, redirect to Login page
    if((request.getSession(false).getAttribute("Author")== null) )
    {
%>
<jsp:forward page="/Login"></jsp:forward>
<%} %>

<head>
    <%@include file="WEB-INF/scripts.jsp"%>
    <title>My Sales</title>
</head>
<body>
<%@ include file="/Author/navbar.jsp" %>

<% author = (Author) session.getAttribute("Author");
    Sales sales = new Sales(author);%>

<div><br>

    <center><h2>My Sales</h2></center></div>

<hr>

<div class="container border rounded">
    <div class="row">
        <div class="col-sm-12">
            <div class="row"><h3>Total Earnings: <%=sales.getTotalEarnings()%> € </h3></div>
            <div class="row"><h3>Total Books Sold: <%=sales.getTotalQuantity()%> </h3></div>
        </div>
    </div>
</div>

<br>
<hr>
<div><br>

    <center><h2>Book Sales:</h2></center></div>
<br>
<% for(SalesItem s : sales.getItems()){
String bookname = new BookDao().getBook(s.getBook_id()).getName();
%>

<div class="container border rounded">
    <div class="row">
        <div class="col-sm-8">
            <div class="row">  <h3>#<%=s.getBook_id() + " - " +bookname%></h3> </div>
            <div class="row"><h4>Earnings: <%=s.getEarnigs()%> € </h4></div>
            <div class="row"><h4>Books Sold: <%=s.getQuantity()%> </h4></div>
        </div>
        <div class = "col-sm-4 my-auto">
            <a href="./sale_details.jsp?book_id=<%=s.getBook_id()%>" class="btn btn-success">See Details</a>
        </div>
    </div>
</div>

<br>

<%} %>

</body>
</html>
