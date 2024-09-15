<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
String contextPath = request.getContextPath();
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="<%=contextPath%>/style/AdminHeader.css">
</head>
<body>
    <header>
        <h1>Admin Dashboard</h1>
        <nav>
            <a href="AdminViewProduct.jsp">Manage Products</a>
            <a href="AddProduct.jsp">Add Product</a>
            <a href="Orders.jsp">View Orders</a>
            <a href="AdminProfilePage.jsp">Profile</a>
            <a href="#" onclick="if (confirm('Are you sure you want to log out?')) { var form = document.createElement('form'); form.method = 'post'; form.action = '../LogoutServlet'; document.body.appendChild(form); form.submit(); }">Logout</a>
        </nav>
    </header>
   
</body>
</html>
