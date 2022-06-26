<%@ page language="java" contentType="text/html; charset=utf-8"
 pageEncoding="utf-8"%>
<%@page import="com.unipi.BookStore.dao.UserDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Success</title>
</head>
<body>
 <h1>User successfully registered!</h1>
 <a href = '<%=request.getContextPath()%>/Login'>Go to Login Page</a>
</body>
</html>