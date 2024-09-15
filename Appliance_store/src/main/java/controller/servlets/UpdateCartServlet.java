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
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateCartServlet" })
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCartServlet() {
        super();
        
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Getting info from jsp
		
		String userId = request.getParameter("user_id");
        String productId = request.getParameter("product_id");
        String quantityStr = request.getParameter("quantity");
        
        System.out.println(userId);
        System.out.println(productId);
        System.out.println(quantityStr);

        // Validate the quantity input to avoid NumberFormatException
        if (quantityStr == null || !quantityStr.matches("\\d+")) {
            request.setAttribute("errorMessage", "Invalid quantity specified");
            request.getRequestDispatcher(request.getContextPath() + "/pages/Cart.jsp").forward(request, response);
            return;
        }
        
        int quantity = Integer.parseInt(quantityStr);
        
        System.out.println(quantity);

        String sql = "UPDATE cart_items SET quantity = ? WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, quantity);
            pstmt.setString(2, userId);
            pstmt.setString(3, productId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect(request.getContextPath() + "/pages/Cart.jsp"); // Redirect back to the cart page
            } else {
                request.setAttribute("errorMessage", "No rows updated, check your input values");
                request.getRequestDispatcher(request.getContextPath() + "/pages/CartTest.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
            throw new ServletException("SQL Error when updating cart items", e);
        }
	}

}
