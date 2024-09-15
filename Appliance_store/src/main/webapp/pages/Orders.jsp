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
    <title>User Orders</title>
    <link rel="stylesheet" href="../style/ViewOrder.css">
</head>
<body>
    <header>
    	<%@ include file="AdminHeader.jsp" %>
        <h1>User Orders</h1>
    </header>

    <main>
        <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/appliance_store" user="root" password=""/>

        <sql:query dataSource="${dataSource}" var="userOrders">
            SELECT * FROM orders;
        </sql:query>

               <table>
                   <thead>
                       <tr>
                           <th>Customer ID</th>
                           <th>Order ID</th>
                           <th>Order Date</th>
                           <th>Total Amount</th>
                           <th>Order Status</th>
                       </tr>
                   </thead>
                   <tbody>
                       <c:forEach items="${userOrders.rows}" var="order">
                           <tr>
                           		<td>${order.user_id}</td>
                               <td>${order.order_id}</td>
                               <td>${order.order_date}</td>
                               <td>$${order.total_amount}</td>
                               <td>${order.order_status}</td> 
                           </tr>
                       </c:forEach>
                   </tbody>
               </table>
    </main>
</body>
</html>
