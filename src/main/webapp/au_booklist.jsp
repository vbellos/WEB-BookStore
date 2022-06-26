<%@ page import="com.unipi.BookStore.model.Book" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<style>
  <%@include file="CSS/bookcard.css"%>
</style>

<head>
</head>
<body>
<%
  ArrayList<Book> books = (ArrayList<Book>) session.getAttribute("booklist");

%>
<div class="container">

  <div class="card">
<div class="row">
  <%
    for(Book book : books) {
      String viewbook = request.getContextPath()+"/AuBook?id="+book.getId();
      String editbook = request.getContextPath()+"/EditBook?id="+book.getId();
      String image =  "./images/"+book.getImage();
      float price =  book.getPrice();
  %>
  <div class="col-md-3 col-sm-6">
    <div class="product-grid">
      <div class="product-image">
        <a href="#" class="image">
          <img class="pic-1" src="<%=image%>" >
        </a>
        <ul class="product-links">
          <li><a href="<%=editbook%>"><i class="fa fa-shopping-bag"></i> Edit Book </a></li>
          <li><a href="<%=viewbook%>"><i class="fa fa-search"></i> Quick View</a></li>
        </ul>
      </div>
      <div class="product-content">
        <h3 class="title"><a href="<%=viewbook%>"><%=book.getName()%></a></h3>
        <div class="price"><%=price%>€</div>
      </div>
    </div>
  </div>
  <%
    }
  %>
</div>
  </div>
</div>
</body>
</html>
