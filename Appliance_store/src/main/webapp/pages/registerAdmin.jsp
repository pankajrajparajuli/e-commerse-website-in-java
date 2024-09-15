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
    <title>Admin Registration</title>
    <link rel="stylesheet" href="<%=contextPath%>/style/registerAdmin.css">
</head>
<body>
    <div class="container">
        <h2>Admin Registration</h2>
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
   		
        <form action="../RegisterAdminServlet" method="POST">
            <label for="username">Username:</label>
            <input type="text" id="admin_name" name="admin_name" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="phone">Phone Number:</label>
            <input type="tel" id="number" name="number" required>

            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>
