<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style/UserHeader.css">
</head>
<body>
    <header>
        <nav class="nav-bar">
            <a href="<%= request.getContextPath() %>/pages/Home.jsp">Home</a>
            <a href="<%= request.getContextPath() %>/pages/AboutUs.jsp">About Us</a>
            <a href="<%= request.getContextPath() %>/pages/Cart.jsp">Cart</a>
            <a href="<%= request.getContextPath() %>/pages/UserOrder.jsp">Order History</a>
            <a href="<%= request.getContextPath() %>/pages/UserProfilePage.jsp">Profile</a>
            <div>
                <form action="<%= request.getContextPath() + (session.getAttribute("user_id") != null ? "/LogoutServlet" : "/pages/loginOption.jsp") %>" method="post">
                    <input type="submit" value="<%= session.getAttribute("user_id") != null ? "Logout" : "Login" %>"/>
                </form>
            </div>
        </nav>
        <h1>Welcome to Appliance Store</h1>
    </header>
</body>
</html>
