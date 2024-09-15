<%@ page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%
String contextPath = request.getContextPath();
String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ALREADY_IN_CART);
String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_ADDED_TO_CART);
String successParam = request.getParameter(StringUtils.MESSAGE_ADDED_TO_CART);
String searchQuery = request.getParameter("search");

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Appliance Store Home</title>
    <link rel="stylesheet" href="<%=contextPath%>/style/Home.css">
</head>
<body>
<%@ include file="UserHeader.jsp" %>
<main class="product-display">

		<!-- Search Form -->
		<form method="POST" action="<%=contextPath%>/SearchServlet">
    		<input type="text" name="search" placeholder="Search products..." value="${empty searchQuery ? '' : searchQuery}">
    		<button type="submit">Search</button>
		</form>
	
   		<%-- Displaying Error Message  --%>
   		
   		<%
   		String errorMessage = (String) request.getAttribute("errorMessage");
   		String successMessage = (String) request.getAttribute("successMessage");
   		String redirectMessage = (String) request.getAttribute("redirectMessage");
   		if (errorMessage != null && !errorMessage.isEmpty()){
   		    %>
   		    <p class="error-message" style="color: red; font-weight: bold; text-decoration: underline;"><%=errorMessage%></p>
   		    <%
   				} else if(redirectMessage != null && !redirectMessage.isEmpty()){
   		    %>
   		    <p class="redirect-message"><%=redirectMessage%></p>
   		    <%
   		    	} else if(successMessage != null && !successMessage.isEmpty()){
   		    %>
   		    <p class="success-message"><%=successParam%></p>
   		    <%
   		    }
   		    %>
    <h2>Featured Appliances</h2>
    <div class="products-container">
    
   		
        <!-- <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                            url="jdbc:mysql://localhost:3306/appliance_store" user="root" password=""/>
        <sql:query dataSource="${dataSource}" var="products">
            SELECT * FROM Products;
        </sql:query> -->
        
        <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                            url="jdbc:mysql://localhost:3306/appliance_store" user="root" password=""/>
        <sql:query dataSource="${dataSource}" var="products">
            <c:choose>
                <c:when test="${empty searchQuery}">
                    SELECT * FROM Products;
                </c:when>
                <c:otherwise>
                    SELECT * FROM Products WHERE product_name LIKE '%${searchQuery}%' OR price LIKE '%${searchQuery}%';
                </c:otherwise>
            </c:choose>
        </sql:query>
        
        <c:forEach items="${products.rows}" var="product">
            <div class="product">
                <img src="${pageContext.request.contextPath}/resources/images/product/${product.imageUrlFromPart}" alt="${product.product_name}" style="width:400px; height:267px;">
                <h3>${product.product_name}</h3>
                <p>${product.description}</p>
                <p>Price: $${product.price}</p>
               <!--   <form action="Cart.jsp?product_id=${product.product_id}&user_id="1"" onsubmit="return confirm('Are you sure you want to add this product to your cart?');"> -->
                <form method="POST" action="<%=contextPath%>/CartServlet" onsubmit="return confirm('Are you sure you want to add this product to your cart?');">
    			<input type="hidden" name="product_id" value="${product.product_id}">
   				<input type="hidden" name="user_id" value= "${sessionScope.user_id}">
    			<!--  Additonal data or inputs  -->
    			<button type="submit">Add to Cart</button>
				</form>
                
                
               <!--   <form action="Cart.jsp?product_id=${product.product_id}&user_id="1"" onsubmit="return confirm('Are you sure you want to add this product to your cart?');">
                   <input type="hidden" name="product_id" value="${product.product_id}" />
                      <input type="hidden" name="user_id" value="1" /> 
                    <button type="submit">Add to Cart</button>
             		 </form> -->
            </div>
        </c:forEach>
    </div>
</main>
</body>

<footer>
    <p>Contact us at support@appliancestore.com</p>
</footer>
</html>
