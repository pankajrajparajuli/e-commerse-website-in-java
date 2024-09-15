package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import utils.StringUtils;


/**
 * Servlet implementation class CartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CartServlet" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/// Creating dbController object of data base controller
	DBController dbController = new DBController();
       
   
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Getting product details from the jsp page
		String productIdStr = request.getParameter("product_id");
	    String userIdStr = request.getParameter("user_id");
	    int quantity = 1 ;
	    
	    
	    // Creating variables that stores integer
	    int product_id = 0;
	    int user_id = 0;

		try {
			if (productIdStr != null && !productIdStr.isEmpty()) {
				product_id = Integer.parseInt(productIdStr);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}
		
		try {
			if (userIdStr != null && !userIdStr.isEmpty()) {
				user_id = Integer.parseInt(userIdStr);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}
	    
		System.out.println(product_id);
		System.out.println(user_id);
	    
				
		// Call DBController to register the student
			int result = dbController.addToCart(user_id, product_id, quantity);
			
			if (result > 0) {
				// IF successfully added to cart
				request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_ADDED_TO_CART);
				response.sendRedirect(request.getContextPath() + StringUtils.CART_URL);
				
			} else if (user_id == 0) {
				// IF the user has't logged in 
				request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.LOGIN_TO_ACCESS_CART);
				request.getRequestDispatcher(StringUtils.USER_HOME).forward(request, response);
				
				
			} else {
				// IF item already added to cart
				request.setAttribute("redirectMessage", StringUtils.MESSAGE_ALREADY_IN_CART);
				request.getRequestDispatcher(StringUtils.USER_HOME).forward(request, response);
				System.out.println("0");
			}
				
	}

}
