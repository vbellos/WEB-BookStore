<%@ page import="com.unipi.BookStore.model.Cart" %>
<%@ page import="com.unipi.BookStore.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: Vaggelis
  Date: 20-May-21
  Time: 10:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="WEB-INF/scripts.jsp"%>
    <title>Cart</title>


    <%@ include file="navbar.jsp" %>
    <%Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null){cart = new Cart();session.setAttribute("cart",cart);}
    List<Book> bl = cart.getBookList();
    int q;
    %>



</head>
<body>
    <div class="container">
        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->


                <div class="col-9 border rounded p-2">
                    <br>
                <h3 class="text-center">Cart</h3>
                <hr>

                <br>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Author</th>
                        <th>Genre</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!--   for (Todo todo: todos) {  -->
                    <% for (Book b :bl){%>
                    <form action="./Cart" method="post">
                        <tr>
                            <td><%=b.getId()%></td>
                            <td><%=b.getName()%></td>
                            <td><%=b.getAuthor()%></td>
                            <td><%=b.getGenre()%></td>
                            <td><%=b.getPrice()%></td>
                            <input type="hidden" id="id" name="id" value="<%=b.getId()%>">
                            <td>
                                <input type="number" size="2" id="quantity" name="quantity" min="1" value="<%=b.getQuantity()%>" >
                            </td>
                            <td><input type="submit" name="update" value="Update">
                                &nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" name="remove" value="Remove"></td>
                        </tr>
                    </form>
                    <%}%>
                    <!-- } -->
                    </tbody>

                </table>
                </div>
                <div class="col-3 px-2 border rounded">
            <div class="container ">
                <br>

                <h3 class="text-center">Checkout</h3>
                <hr>
                <h5>Total: <%=cart.getTotal()%>€</h5>
                <a class="btn btn-success" href="./Cart?buy=true">Buy Now</a>

                <a class="btn btn-success" href="<%=request.getHeader("referer")%>">Go Back</a>
                <br>
            </div><br>
        </div>

        </div>
    </div>
</body>
</html>
