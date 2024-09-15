<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%
    // Check if admin is logged in
    Integer adminId = (Integer) session.getAttribute("admin_id");
    if (adminId == null) {
        response.sendRedirect(request.getContextPath() + "/pages/loginAdmin.html");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
    <link rel="stylesheet" href="../style/UpdateProfile.css">
</head>
<body>
<header>
    <%@ include file="AdminHeader.jsp" %>
    <h1>Update Profile</h1>
</header>
<main>
    <form action="../UpdateProfileAdminServlet" method="post">
        <input type="hidden" id="admin_id" name="admin_id" value="<%=adminId%>">
        
        <label for="admin_name">Username:</label>
        <input type="text" id="admin_name" name="admin_name" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="number">Phone Number:</label>
        <input type="tel" id="number" name="number" required>
        <br>
        <input type="submit" value="Update">
    </form>
</main>
<footer>
    <p>&copy; 2024 My Website</p>
</footer>
</body>
</html>
