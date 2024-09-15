<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<c:if test="${empty sessionScope.user_id}">
    <c:redirect url="${request.getContextPath()}/pages/loginOption.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Order History</title>
    <link rel="stylesheet" href="<c:url value='/style/ViewOrder.css'/>"> 
</head>
<body>
    <%@ include file="UserHeader.jsp" %>
     
    <main>
        <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/appliance_store" user="root" password=""/>

        <sql:query dataSource="${dataSource}" var="userOrders">
            SELECT o.order_id, o.order_date, o.total_amount, o.order_status,
                   od.product_id, p.product_name, p.price
            FROM orders o
            INNER JOIN orderdetails od ON o.order_id = od.order_id
            INNER JOIN products p ON od.product_id = p.product_id
            WHERE o.user_id = ?;
            <sql:param value="${sessionScope.user_id}" />
        </sql:query>

        <c:if test="${empty userOrders.rows}">
            <p>No orders found for this user.</p>
        </c:if>

        <c:if test="${not empty userOrders.rows}">
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Product Name</th>
                        <th>Product Price</th> 
                        <th>Total Amount</th> 
                        <th>Order Status</th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${userOrders.rows}" var="order">
                        <tr>
                            <td>${order.order_id}</td>
                            <td>${order.order_date}</td>
                            <td>${order.product_name}</td>
                            <td>$${order.price}</td> 
                            <td>$${order.total_amount}</td> 
                            <td>${order.order_status}</td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </main>
</body>
</html>
