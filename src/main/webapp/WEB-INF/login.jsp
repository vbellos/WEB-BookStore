<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="scripts.jsp"%>
<head>
    <title>Login</title>
</head>
<body>
<header>

</header>
<%@ include file="../navbar.jsp" %>
<br>
<br>
<div align="center">

    <div class="container">


        <div class="container">
        <form action="<%=request.getContextPath()%>/Login" method = "post" class="m-auto" style="max-width:600px">
            <h3 class="my-4">Login Form</h3>
            <hr class="my-4" />
            <div class="form-group mb-3 row"><label for="username-2" class="col-md-5 col-form-label">Username:</label>
                <div class="col-md-7"><input type="text" class="form-control form-control-lg" id="username-2" name="username" required></div>
            </div>
            <div class="form-group mb-3 row"><label for="password-3" class="col-md-5 col-form-label">Password:</label>
                <div class="col-md-7"><input type="password" class="form-control form-control-lg" id="password-3" name="password" required></div>
            </div>
            <hr class="bg-transparent border-0 py-1" />
            <hr class="my-4" />
            <div class="form-group mb-3 row"><label for="login6" class="col-md-5 col-form-label"></label>
                <div class="col-md-7"><button class="btn btn-success btn-lg" type="submit">Login</button></div>
            </div>
            <hr class="my-4" />

        </form>
    </div>

</div>
</body>
</html>