<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // Check if admin is logged in
    Integer adminId = (Integer) session.getAttribute("admin_id");
    if (adminId == null) {
        response.sendRedirect(request.getContextPath() + "/pages/loginAdmin.html");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Product - Admin Panel</title>
    <link rel="stylesheet" href="../style/AddNewProduct.css">
</head>
<body>
    <header>
    	<%@ include file="AdminHeader.jsp" %>
        <h1>Add New Product</h1>
  
    </header>
    <main>
        <form action="../AddProductServlet" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="productName">Product Name:</label>
                <input type="text" id="product_name" name="product_name" required>
            </div>
            <div class="form-group">
                <label for="productPrice">Price:</label>
                <input type="number" id="price" name="price" step="0.01" required>
            </div>
            <div class="form-group">
                <label for="productDescription">Description:</label>
                <textarea id="description" name="description" rows="4" required></textarea>
            </div>
            <div class="form-group">
                <label for="productStock">Stock:</label>
                <input type="number" id="stock" name="stock" required>
            </div>
            <div class="form-group">
                <label for="productImage">Product Image:</label>
                <input type="file" id="image" name="image" required>
            </div>
            <button type="submit">Add Product</button>
        </form>
    </main>
</body>
</html>
