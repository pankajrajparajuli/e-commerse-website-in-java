<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="utils.StringUtils" %>

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
    <title>Welcome Admin</title>
    <link rel="stylesheet" type="text/css" href="../style/WelcomeAdmin.css">
</head>
<body>
	<%
		String adminName = (String) request.getAttribute("admin_name");
		%>

    <%@ include file="AdminHeader.jsp" %>
    <header>
        <h1>Welcome Admin</h1>
    </header>
   
   
    <main>
        <p>Welcome <%= session.getAttribute("admin_name") %> to the admin panel. Your admin ID is <%= adminId %>. You have access to manage products and view orders.</p>
    </main>
    <footer>
        <p>&copy; 2024 Your Company</p>
    </footer>
</body>
</html>

