package controller.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.StringUtils;

/**
 * Servlet implementation class RemoveCartItems
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RemoveItemServlet" })
public class RemoveItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RemoveItemServlet() {
        super();
      
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("user_id");
        String productId = request.getParameter("product_id");

        System.out.println(userId);
        System.out.println(productId);
        
        String sql = "DELETE FROM cart_items WHERE user_id = ? AND product_id = ?";

        // Use try-with-resources to connect to and interact with the database
        try (Connection conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            stmt.setString(1, userId);
            stmt.setString(2, productId);

            // Execute the update
            int rowsAffected = stmt.executeUpdate();

            // Redirect or forward to another page based on the outcome
            if (rowsAffected > 0) {
                response.sendRedirect(request.getContextPath() + "/pages/Cart.jsp");  // Redirect back to the cart page or a confirmation page
            } else {
                request.setAttribute("errorMessage", "Error removing product from cart");
                request.getRequestDispatcher(request.getContextPath() + "/pages/Cart.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("SQL error when removing item from cart", e);
        }
		
	}

}
