<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Product</title>
    <link rel="stylesheet" href="../style/updateProduct.css">
</head>
<body>

<header>
        <h1>Update Product</h1>
        <nav>
            <a href="ManageProducts.html">Back to Management</a> 
        </nav>
    </header>
    <main>
        <%-- Retrieve the product_id from the request URL --%>
        <%@ page import ="java.sql.Statement" %>
        <%@ page import ="java.sql.DriverManager" %>
        <%@ page import ="java.sql.Connection" %>
        <%@ page import ="java.sql.Statement" %>
        <%@ page import ="java.sql.ResultSet" %>
        
        <% String productId = request.getParameter("product_id");
        	Integer product_id = Integer.parseInt(productId);
        	
        			Class.forName("com.mysql.jdbc.Driver");
        			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/appliance_store","root","");
        			Statement st = con.createStatement();
        			ResultSet resultSet = st.executeQuery("SELECT * FROM products WHERE product_id='"+product_id+"'");
        	
        	%>
        	<div class ="card-body">
        	<form action="../UpdateProductServlet" method="post" enctype="multipart/form-data">
        	<% while(resultSet.next()){%>
            <!--  <form action="../UpdateProductServlet" method="post" enctype="multipart/form-data"> -->
                <input type="hidden" name="product_id" value="<%=product_id%>"> 
                <div class="form-group">
                    <label for="productName">Product Name:</label>
                    <input type="text" id="product_name" name="product_name" >
                </div>
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" name="description"></textarea>
                </div>
                <div class="form-group">
                    <label for="price">Price:</label>
                    <input type="number" id="price" name="price">
                </div>
                <div class="form-group">
                    <label for="stock">Stock Level:</label>
                    <input type="number" id="stock" name="stock" min="0">
                </div>
                <div class="form-group">
                    <label for="image">Product Image:</label>
                    <input type="file" id="image" name="image">
                </div>
                <button type="submit">Update Product</button>
               <%} %>
            </form>
        </div>
    </main>
    

</body>
</html>