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
    <title>User Profile</title>
    <link rel="stylesheet" href="../style/AdminProfile.css">
    <!-- Include external header file -->
   <%@ include file="AdminHeader.jsp" %>
   
</head>
<body>
    <main>
        <div class="profile-card">
            <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/appliance_store" user="root" password=""/>
        
            <sql:query dataSource="${dataSource}" var="userProfile">
                SELECT * FROM admins WHERE admin_id = <%=adminId %>;
            </sql:query>
            
            <c:forEach items="${userProfile.rows}" var="admin">
                <div class="profile-info">
                    <h2>${admin.admin_name}</h2>
                    <p>Email: ${admin.email}</p>
                    <p>Number: ${admin.number}</p>
                    <p>ID: ${admin.admin_id}</p>
                    <a href="UpdateAdminProfile.jsp">Edit Profile</a>
                </div>
            </c:forEach>
        </div>
    </main>
    <footer>
        <p>&copy; 2024 My Website</p>
    </footer>
</body>
</html>
