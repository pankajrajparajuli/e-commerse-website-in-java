<%@ page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String contextPath = request.getContextPath();
String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_WRONG_INFO);

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="<%=contextPath%>/style/login.css">
</head>
<body>
    <div class="container">
        <h2>User/Login</h2>
        <%
   		String errorMessage = (String) request.getAttribute("errorMessage");
   		String successMessage = (String) request.getAttribute("successMessage");
   		if (errorMessage != null && !errorMessage.isEmpty()){
   		%>
   		<p class="error-message" style="color: red; font-weight: bold; text-decoration: underline;"><%=errorMessage%></p>
   		<%
   		}else if(successMessage != null && !successMessage.isEmpty()){
   		%>
   		<%
   		}
   		%>
        <form action="<%=contextPath%>/LoginUserServlet" method="post">
            <div class="input-group">
                <label for="username">Username:</label>
                <input type="text" id="user_name" name="user_name" required>
            </div>
            <div class="input-group">
                <label for="password">Password:</label>
                <input type="password" id="user_password" name="user_password" required>
            </div>
            <button type="submit">Login</button>
            <p class="register-link">Don't have an account? <a href="<%=contextPath%>/pages/registerUser.jsp">Register</a></p> <!-- Added link to registration page -->
        </form>
    </div>
</body>
</html>
