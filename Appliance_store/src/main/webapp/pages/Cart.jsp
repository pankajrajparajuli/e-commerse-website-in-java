<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<c:if test="${empty sessionScope.user_id}">
    <c:redirect url="${request.getContextPath()}/pages/loginOption.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Shopping Cart</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/Cart.css">
</head>
<body>
    <header>
       <%@ include file="UserHeader.jsp" %>
    </header>

    <main>
        <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/appliance_store" user="root" password=""/>

        <sql:query dataSource="${dataSource}" var="cartItems">
            SELECT ci.user_id, ci.product_id, p.product_name, p.price, ci.quantity,
                   (p.price * ci.quantity) AS total_price
            FROM cart_items ci 
            JOIN products p ON ci.product_id = p.product_id 
            WHERE ci.user_id = ${sessionScope.user_id};
        </sql:query>

        <table>
            <thead>
                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total Price</th> 
                    <th>Actions</th> 
                </tr>
            </thead>
            <tbody>
                <c:set var="cartTotal" value="0" />
                <c:forEach items="${cartItems.rows}" var="item">
                    <tr>
                        <td>${item.product_name}</td>
                        <td>$${item.price}</td> 
                        <td>
                            <form action="<c:url value='/UpdateCartServlet'/>" method="post">
                            	<input type="hidden" name="user_id" value="${sessionScope.user_id}" />
                                <input type="hidden" name="product_id" value="${item.product_id}" />
                                <input type="number" name="quantity" value="${item.quantity}" min="1" />
                                <button type="submit">Update</button>
                            </form>
                        </td>
                        <td>$${item.total_price}</td> 
                        <td>
                            <form action="<c:url value='/RemoveItemServlet'/>" method="post">
                            	<input type="hidden" name="user_id" value="${sessionScope.user_id}" />
                                <input type="hidden" name="product_id" value="${item.product_id}" />
                                <button type="submit">Remove</button>
                            </form>
                        </td>
                    </tr>
                    <c:set var="cartTotal" value="${cartTotal + item.total_price}" />
                </c:forEach>
            </tbody>
            <tfoot> 
                <tr>
                    <td colspan="3" style="text-align:right;">Total:</td>
                    <td>$${cartTotal}</td>
                    <td></td>
                </tr>
            </tfoot>
        </table>

        <div style="text-align:right; margin-top:20px;">
            <form action="<c:url value='/CheckoutServlet'/>" method="post">
                <button type="submit" style="padding:10px 30px; font-size:16px; background-color:#4CAF50; color:white; border:none; border-radius:15px; cursor:pointer;">Buy Now</button>
            </form>
        </div>
    </main>

    <footer>
        <p>Contact us at support@appliancestore.com</p>
    </footer>
</body>
</html>
