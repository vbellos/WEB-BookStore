<%@ page import="com.unipi.BookStore.model.Client" %>
<%@ page import="com.unipi.BookStore.dao.BookDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.unipi.BookStore.model.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<body>
<style>
    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
    }
</style>
<%
    BookDao bd = new BookDao();
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="./Home">Book Store</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarColor01">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="./Home?all=true">My Books</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./addbook.jsp">Add New Book</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="./mysales.jsp">My Sales</a>
                </li>

            </ul>

        </div>
        <ul class="d-flex me-auto mb-2 mb-lg-0 px-4">
            <div class="collapse navbar-collapse">

                <%
                    Author author = (Author) session.getAttribute("Author");
                    if (author != null) {

                %>

                <li class="nav-item dropdown">
                    <a class="h-100 btn btn-outline-success dropdown-toggle" data-toggle="dropdown" href="#"><i class="bi bi-person-circle"></i> <%=author.getUsername()%> <span class="caret"></span> </a>
                    <div class="dropdown-menu " aria-labelledby="navbarDropdown">

                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Account">Account</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Messages">Messages</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Logout">Logout</a>

                    </div>
                </li>
                <%
                } else {
                    // Not logged in, show login prompt
                %>

                <li class="nav-item px-2"><a class="btn btn-outline-success nav-link" href="<%=request.getContextPath()%>/Register"> Register</a></li>
                <li class="nav-item px-2"><a class="btn btn-outline-success nav-link" href="<%=request.getContextPath()%>/Login"> Login</a></li>
                <%
                    }
                %>
            </div>
        </ul>
    </div>


</nav>
</body>
</html>