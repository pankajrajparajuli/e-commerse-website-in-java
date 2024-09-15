<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%
    // Check if admin is logged in
    Integer userId = (Integer) session.getAttribute("user_id");
    if (userId == null) {
        response.sendRedirect(request.getContextPath() + "/pages/loginUser.jsp");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/UpdateUserProfile.css">

</head>
<body>
<header>
    <%@ include file="UserHeader.jsp" %>
    <h1>Update User Profile</h1>
</header>
<main>
    <form action="../UpdateProfileUserServlet" method="post">
        <input type="hidden" id="user_id" name="user_id" value="<%= userId %>">
        
        <label for="admin_name">Username:</label>
        <input type="text" id="user_name" name="user_name" required>

        <label for="email">Email:</label>
        <input type="email" id="user_email" name="user_email" required>

        <label for="password">Password:</label>
        <input type="password" id="user_password" name="user_password" required>

        <label for="number">Phone Number:</label>
        <input type="tel" id="user_number" name="user_number" required>
        <br>
        <input type="submit" value="Update">
    </form>
</main>
<footer>
    <p>&copy; 2024 My Website</p>
</footer>
</body>
</html>
