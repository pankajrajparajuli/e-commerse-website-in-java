<%@ page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
String contextPath = request.getContextPath();
String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ENTER_UNIQUE_INFO);

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <link rel="stylesheet" href="<%=contextPath%>/style/registerUser.css">
</head>
<body>
    <div class="container">
        <h2>User Registration</h2>
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
        <form action="../RegisterUserServlet" method="POST">
            <label for="username">Username:</label>
            <input type="text" id="user_name" name="user_name" required>

            <label for="email">Email:</label>
            <input type="email" id="user_email" name="user_email" required>

            <label for="password">Password:</label>
            <input type="password" id="user_password" name="user_password" required>

            <label for="phone">Phone Number:</label>
            <input type="tel" id="user_number" name="user_number" required>

            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>
