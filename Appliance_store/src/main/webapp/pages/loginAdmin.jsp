<%@ page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
        <h2>Admin/Login</h2>
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
        <form action="../LoginAdminServlet" method="post">
            <div class="input-group">
                <label for="username">Username:</label>
                <input type="text" id="admin_name" name="admin_name" required>
            </div>
            <div class="input-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Login</button>
            <p class="register-link">Don't have an account? <a href="<%=contextPath%>/pages/registerAdmin.jsp">Register</a></p> <!-- Added link to registration page -->
        </form>
    </div>
</body>
</html>
