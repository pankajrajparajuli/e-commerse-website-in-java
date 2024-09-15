<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    Integer userId = (Integer) session.getAttribute("user_id");
    if (userId == null) {
        response.sendRedirect(request.getContextPath() + "/pages/loginOption.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us</title>
    <link rel="stylesheet" href="../style/AboutUs.css">
</head>
<body>
	<%@ include file="UserHeader.jsp" %>
    <div class="container">
        <h2>About Us</h2>
        <div class="contact-info">
            <h3>Contact Information</h3>
            <p>Phone: +123456789</p>
            <p>Email: info@example.com</p>
        </div>
        <div class="message-form">
            <h3>Send Us a Message</h3>
            <form action="#" method="post">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
                <label for="message">Message:</label>
                <textarea id="message" name="message" rows="4" required></textarea>
                <button type="submit">Send</button>
            </form>
        </div>
    </div>
</body>
</html>
