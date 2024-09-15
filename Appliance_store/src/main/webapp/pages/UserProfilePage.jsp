<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<c:if test="${empty sessionScope.user_id}">
    <c:redirect url="${request.getContextPath()}/pages/loginUser.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="stylesheet" href="<c:url value='/style/AdminProfile.css'/>">
</head>
<body>
    <%@ include file="UserHeader.jsp" %>
   
    <main>
        <div class="profile-card">
            <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/appliance_store" user="root" password=""/>
        
            <sql:query dataSource="${dataSource}" var="userProfile">
                SELECT * FROM users WHERE user_id = ${sessionScope.user_id};
            </sql:query>
            
            <c:forEach items="${userProfile.rows}" var="admin">
                <div class="profile-info">
                    <h2>${admin.user_name}</h2>
                    <p>Email: ${admin.user_email}</p>
                    <p>Number: ${admin.user_number}</p>
                    <p>ID: ${admin.user_id}</p>
                    <a href="UpdateUserProfile.jsp">Edit Profile</a>
                </div>
            </c:forEach>
        </div>
    </main>
    
    <footer>
        <p>&copy; 2024 My Website</p>
    </footer>
</body>
</html>
