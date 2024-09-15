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
    <title>Admin View Products</title>
    <link rel="stylesheet" href="../style/AdminView.css">
</head>
<body>
<header>
	<%@ include file="AdminHeader.jsp" %>
    <h1>Product Management</h1>
</header>
<main>
    <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                        url="jdbc:mysql://localhost:3306/appliance_store" user="root" password=""/>
    <sql:query dataSource="${dataSource}" var="products">
        SELECT * FROM products;
    </sql:query>
    
    <div class="products-container">
        <c:forEach items="${products.rows}" var="products">
            <div class="product">
            	<div class="product-image">
                <img src="${pageContext.request.contextPath}/resources/images/product/${products.imageUrlFromPart}" alt="${products.product_name}">
                </div>
                <h3>${products.product_name}</h3>
                <p>${products.description}</p>
                <p>Price: $${products.price}</p>
                <form action="updateProduct.jsp?product_id=${products.product_id}" method="post">
                    <input type="hidden" name="product_id" value="${products.product_id}" />
                    <button type="submit">Update</button>
                </form>
                <form action="../DeleteProductServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this product?');">
                    <input type="hidden" name="product_id" value="${products.product_id}" />
                    <button type="submit">Delete</button>
                </form>
            </div>
        </c:forEach>
    </div>
</main>
<footer>
    <p>Contact us at support@appliancestore.com</p>
</footer>
</body>
</html>
