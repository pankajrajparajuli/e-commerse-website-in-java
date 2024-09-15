<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String contextPath = request.getContextPath();
%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Selection</title>
    <link rel="stylesheet" href="<%=contextPath%>/style/loginOption.css">
</head>
<body>
    <div class="login-selection">
        <h1>Login Portal</h1>
        <div class="login-options">
            <a href="<%=contextPath%>/pages/loginAdmin.jsp" class="login-link admin">Login as Admin</a>
            <a href="<%=contextPath%>/pages/loginUser.jsp" class="login-link user">Login as User</a>
        </div>
    </div>
</body>
</html>
