<%@ page import="com.unipi.BookStore.model.Book" %>
<%@ page import="com.unipi.BookStore.model.Review" %><%--
  Created by IntelliJ IDEA.
  User: Vaggelis
  Date: 20-May-21
  Time: 12:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="WEB-INF/scripts.jsp"%>
    <%
        Book book = (Book) request.getAttribute("Book");
        if (book != null) {

            String addtocart = request.getContextPath()+"/Cart?add=1&id="+book.getId();
            String image =  "./images/"+book.getImage();
            String message = "./Messages?user="+book.getAuthor();


    %>


    <title><%=book.getName()%></title>
    <%@ include file="navbar.jsp" %>

    <style>
        <%@ include file="/CSS/bookview.css" %>
    </style>



</head>
<body>

<div class="container">

    <hr>

    <div class="card">
        <div class="row">
            <aside class="col-sm-5 border-right">
                <article class="gallery-wrap">
                    <div class="img-big-wrap">
                        <div> <a href="#"><img src="<%=image%>"></a></div>
                    </div>

                </article> <!-- gallery-wrap .end// -->
            </aside>
            <aside class="col-sm-7">
                <article class="card-body p-5">
                    <h3 class="title mb-3"><%=book.getName()%></h3>

                    <p class="price-detail-wrap">
	<span class="price h3 text-warning">
		<span class="num"><%=book.getPrice()%>€</span>
	</span>
                    </p> <!-- price-detail-wrap .// -->
                    <dl class="item-property">
                        <dt>Description</dt>
                        <dd><p><%=book.getDescription()%></p></dd>
                    </dl>
                    <dl class="param param-feature">
                        <dt>id#</dt>
                        <dd><%=book.getId()%></dd>
                    </dl>  <!-- item-property-hor .// -->
                    <dl class="param param-feature">
                        <dt>Author</dt>
                        <dd><%=book.getAuthor()%> <a href="<%=message%>" class="btn btn-secondary"><i class="bi bi-chat-dots"></i></a></dd>
                    </dl>  <!-- item-property-hor .// -->
                    <dl class="param param-feature">
                        <dt>Genre</dt>
                        <dd><%=book.getGenre()%></dd>
                    </dl>  <!-- item-property-hor .// -->
                    <dl class="param param-feature">
                        <dt>Rating</dt>
                        <dd><%=book.getRating()%></dd>
                    </dl>  <!-- item-property-hor .// -->
                    <dl class="param param-feature">
                        <dt>Sold</dt>
                        <dd><%=book.getSold()%></dd>
                    </dl>  <!-- item-property-hor .// -->



                    <!-- row.// -->
                        <hr>

                        <a href="<%=addtocart%>" class="btn btn-lg btn-outline-primary text-uppercase"> <i class="fas fa-shopping-cart"></i> Add to cart </a>
                        <a onclick="showReviews()" class="btn btn-lg btn-outline-primary text-uppercase"> <i class="fas fa-shopping-cart"></i> Reviews </a>
                </article> <!-- card-body.// -->
            </aside> <!-- col.// -->
        </div> <!-- row.// -->
    </div>
    <!-- card.// -->
    <div class="container border rounded">
        <div class="row">
            <div class="col-md-12">

                <div class=" d-flex ">

                    <h3>Add a review: </h3>
                </div>

                <form action="./Book">
                    <div class="row">
                        <div class="col-9">
                            <textarea class="w-100 p-3" name="review" required="">    </textarea>

                        </div>
                        <div class="col-3">
                            <center>
                                <input type="hidden" value="<%=book.getId()%>" name="id">
                                <label for="rating">Rating: </label>

                                <input type="number" id="rating" name="rating" min="1" max="10" value="10" required="">
                                <br>
                                <input type="submit" class="btn btn-success">
                            </center>
                        </div>
                    </div>
                </form>

            </div>
        </div>

    </div>
    <!--Review.//-->


    <!---Other Reviews.//--->

    <div class="container border rounded" id="reviews" style="display: none;">
  <% for (Review r: book.get_reviews()){ %>
        <div class="container-fluid">
            <h4 class="text-left"><%=r.getUsername()%> </h4>
            <h4 class="text-right"><%=r.getRating()%>/10</h4>
        </div>
        <div>
            <textarea class="w-100 p-3" readonly>  <%=r.getComment()%>  </textarea>
        </div>

        <% } %>

    </div>
    <!---Other Reviews.//--->

</div>
<!--container.//-->

<script>function showReviews() {
    var x = document.getElementById("reviews");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}</script>

</body>
<%}
else{request.setAttribute("Book",null);
    request.getRequestDispatcher("/Home").forward(request,response);}
%>
</html>
