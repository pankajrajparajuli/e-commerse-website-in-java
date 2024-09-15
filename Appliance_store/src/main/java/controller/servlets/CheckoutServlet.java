package controller.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.StringUtils;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CheckoutServlet" })
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CheckoutServlet() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id"); // Make sure 'user_id' is set in session on login
        //if (userId == null) {
           // response.sendRedirect("login.jsp"); // Redirect to login if not logged in
            //return;
       // } 
		
		// Hardcoded user ID for testing
        

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);
            conn.setAutoCommit(false); // Start transaction

            // Insert new order and get generated order ID
            String insertOrderSQL = "INSERT INTO orders (user_id, order_date, total_amount, order_status) VALUES (?, NOW(), 0, 'Processing')";
            PreparedStatement psInsertOrder = conn.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            psInsertOrder.setInt(1, userId);
            psInsertOrder.executeUpdate();
            ResultSet rsOrder = psInsertOrder.getGeneratedKeys();
            int orderId = 0;
            if (rsOrder.next()) {
                orderId = rsOrder.getInt(1);
            }
            rsOrder.close();
            psInsertOrder.close();

            // Transfer cart items to order details
            String selectCartItemsSQL = "SELECT product_id, quantity FROM cart_items WHERE user_id = ?";
            PreparedStatement psSelectCartItems = conn.prepareStatement(selectCartItemsSQL);
            psSelectCartItems.setInt(1, userId);
            ResultSet rsCartItems = psSelectCartItems.executeQuery();
            double totalAmount = 0.0;
            while (rsCartItems.next()) {
                int productId = rsCartItems.getInt("product_id");
                int quantity = rsCartItems.getInt("quantity");

                String insertOrderDetailSQL = "INSERT INTO orderdetails (order_id, product_id, quantity) VALUES (?, ?, ?)";
                PreparedStatement psInsertOrderDetail = conn.prepareStatement(insertOrderDetailSQL);
                psInsertOrderDetail.setInt(1, orderId);
                psInsertOrderDetail.setInt(2, productId);
                psInsertOrderDetail.setInt(3, quantity);
                psInsertOrderDetail.executeUpdate();
                psInsertOrderDetail.close();

                // Calculate total amount  price information needs to be pulled from the products table
                String selectProductPriceSQL = "SELECT price FROM products WHERE product_id = ?";
                PreparedStatement psSelectProductPrice = conn.prepareStatement(selectProductPriceSQL);
                psSelectProductPrice.setInt(1, productId);
                ResultSet rsProductPrice = psSelectProductPrice.executeQuery();
                if (rsProductPrice.next()) {
                    double price = rsProductPrice.getDouble("price");
                    totalAmount += price * quantity;
                }
                rsProductPrice.close();
                psSelectProductPrice.close();
            }
            rsCartItems.close();
            psSelectCartItems.close();

            // Update total amount in orders
            String updateOrderSQL = "UPDATE orders SET total_amount = ? WHERE order_id = ?";
            PreparedStatement psUpdateOrder = conn.prepareStatement(updateOrderSQL);
            psUpdateOrder.setDouble(1, totalAmount);
            psUpdateOrder.setInt(2, orderId);
            psUpdateOrder.executeUpdate();
            psUpdateOrder.close();

            // Clear the cart
            String deleteCartSQL = "DELETE FROM cart_items WHERE user_id = ?";
            PreparedStatement psDeleteCart = conn.prepareStatement(deleteCartSQL);
            psDeleteCart.setInt(1, userId);
            psDeleteCart.executeUpdate();
            psDeleteCart.close();

            conn.commit(); // Commit the transaction

            response.sendRedirect(request.getContextPath() + "/pages/UserOrder.jsp");
        } catch (SQLException ex) {
            try {
                if (conn != null) conn.rollback(); // Rollback the transaction on error
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new ServletException("Database error during checkout", ex);
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

}
