package controller.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = { "/DeleteProductServlet"})
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        String productId = request.getParameter("product_id"); // Adjusted parameter name

        if (productId != null) {
            deleteProduct(productId);
            response.sendRedirect(request.getContextPath() + "/pages/AdminViewProduct.jsp");
            // Redirect to the products page or a confirmation page
        } else {
            response.sendRedirect("error.jsp"); // Redirect to an error page
        }
    }

    private void deleteProduct(String productId) { // Adjusted parameter name
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(StringUtils.DRIVER_NAME);
            conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);

            String sql = "DELETE FROM products WHERE product_id = ?"; /// Can be used from String Utils
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
