<%@ page import="com.unipi.BookStore.dao.TransDao" %>
<%@ page import="com.unipi.BookStore.model.BookTrans" %>
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


<br>
<% String b_id = request.getParameter("book_id");
    int id;
if (b_id != null){id = Integer.parseInt(b_id); } else{id =-1;}
%>

<%List<BookTrans> bt = new TransDao().getBookAllTrans(id);%>


<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center"># <%=b_id%></h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Datetime</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
            </tr>
            </thead>
            <tbody>
            <%for (BookTrans b : bt){
            %>
                <tr>
                    <td><%=b.getDateTime()%></td>
                    <td><%=b.getPrice()%></td>
                    <td><%=b.getQuantity()%></td>
                    <td><%=b.getTotal()%></td>
                </tr>
            <%}%>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>


</body>
</html>
