<%@ page import="com.unipi.BookStore.model.Client" %>
<%@ page import="com.unipi.BookStore.dao.BookDao" %>
<%@ page import="java.util.List" %>
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
                    <a class="nav-link active" aria-current="page" href="./Home?all=true">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Features</a>
                </li>


                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Genre
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <%
                            List<String> genres = bd.getGenres();
                            for(String genre : genres){
                        %>
                        <a class="dropdown-item" href="./Search?Genre=<%=genre%>"><%=genre%></a>
                        <div class="dropdown-divider"></div>
                        <%}%>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Author
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <%
                        List<String> authors = bd.getAuthors();
                        for(String author : authors){
                    %>
                        <a class="dropdown-item" href="./Search?Author=<%=author%>"><%=author%></a>
                        <div class="dropdown-divider"></div>
                        <%}%>
                    </div>
                </li>
            </ul>
            <form class="d-flex me-auto mb-2 mb-lg-0 px-4" action="./Search">
                <ul class="d-flex me-auto mb-2 mb-lg-0 px-4">
                    <li class="px-2"><input class="form-control me-2" name="key" type="search" placeholder="Search" aria-label="Search"></li>

                    <li class="px-2"><button class="btn btn-outline-success" type="submit">Search</button></li>
                </ul>
            </form>
        </div>
        <ul class="d-flex me-auto mb-2 mb-lg-0 px-4">
            <div class="collapse navbar-collapse">
                <li class="nav-item px-2">
                    <a class="h-100 btn btn-outline-success nav-link" href="./Cart"><i class="bi bi-cart4"></i> Cart<span class="caret"></span></a>
                </li>
                <%
                    Client client = (Client) session.getAttribute("Client");
                    if (client != null) {

                %>

                <li class="nav-item dropdown">
                    <a class="h-100 btn btn-outline-success dropdown-toggle" data-toggle="dropdown" href="#"><i class="bi bi-person-circle"></i> <%=client.getUsername()%> <span class="caret"></span> </a>
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