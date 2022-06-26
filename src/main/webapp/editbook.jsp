<%@ page import="com.unipi.BookStore.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% //In case, if Author session is not set, redirect to Login page
    if((request.getSession(false).getAttribute("Author")== null) )
    {
%>
<jsp:forward page="/Login"></jsp:forward>
<%} %>



<%@include file="WEB-INF/scripts.jsp"%>
<head>
    <title>Edit Book</title>

</head>
<body>
<%@include file="Author/navbar.jsp"%>
<br>
<%
    Book book = (Book) request.getAttribute("Book");
    if (book != null) {

        String addtocart = request.getContextPath()+"/Cart?add=1&id="+book.getId();
        String image =  "./images/"+book.getImage();


%>
<div class = "container">
    <form  action="./EditBook" method="post" >
        <input type = "hidden" name="edit" value="true">
        <input type = "hidden" name="id" value="<%=book.getId()%>">
        <div class="rendered-form">
            <div class="">
                <h3 access="false" id="control-2844380">Edit Book</h3></div>
            <div class="formbuilder-text form-group field-title">
                <label for="title" class="formbuilder-text-label">Title<span class="formbuilder-required">*</span></label>
                <input type="text" class="form-control" name="title" access="false" maxlength="45" id="title" required="required" aria-required="true" value="<%=book.getName()%>">
            </div>
            <div class="formbuilder-text form-group field-genre">
                <label for="genre" class="formbuilder-text-label">Genre<span class="formbuilder-required">*</span></label>
                <input type="text" class="form-control" name="genre" access="false" maxlength="45" id="genre" required="required" aria-required="true" value="<%=book.getGenre()%>">
            </div>
            <div class="formbuilder-textarea form-group field-desc">
                <label for="desc" class="formbuilder-textarea-label">Description<span class="formbuilder-required">*</span></label>
                <textarea type="textarea" class="form-control" name="desc" access="false" rows="4" id="desc" required="required" aria-required="true" ><%=book.getDescription()%></textarea>
            </div>
            <div class="formbuilder-number form-group field-price">
                <label for="price" class="formbuilder-number-label">Price<span class="formbuilder-required">*</span></label>
                <input type="number" class="form-control" name="price" access="false" min="0" step="0.01" id="price" required="required" aria-required="true" value="<%=book.getPrice()%>">
            </div>
            <div class="formbuilder-button form-group field-button">
                <button type="submit" class="btn-success btn" name="button" access="false" style="success" id="button">Finish</button>
            </div>
        </div>

    </form>

</div>

<div class = "container">
    <form  action="./EditBook" method="post" enctype="multipart/form-data">

        <div class="rendered-form">
            <div class="">
                <h3 access="false" id="control-2q44380">Update Cover</h3></div>
            <input type="hidden" name="id" value="<%=book.getId()%>">
        <div class="formbuilder-file form-group field-file">
        <label for="file" class="formbuilder-file-label">Book Cover<span class="formbuilder-required">*</span></label>
        <input type="file" class="form-control" name="file" access="false" multiple="false" id="file" required="required" aria-required="true">
    </div>
    <div class="formbuilder-button form-group field-button">
        <button type="submit" class="btn-success btn" name="button" access="false" style="success" id="button2">Update</button>
    </div>
</div>

    </form>

</div>

<% } %>
</body>
</html>
