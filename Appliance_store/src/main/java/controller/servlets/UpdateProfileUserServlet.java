package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.RegisterUserModel;

/**
 * Servlet implementation class UpdateProfileUserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProfileUserServlet" })
public class UpdateProfileUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DBController dbController = new DBController();
	
    public UpdateProfileUserServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String user_idStr = request.getParameter("user_id");
		
		System.out.println(user_idStr);
		String user_name = request.getParameter("user_name");
		String user_email = request.getParameter("user_email");
		String user_password = request.getParameter("user_password");
		String user_number = request.getParameter("user_number");
		
		int user_id = 0;
		
		try {
			if (user_idStr != null && !user_idStr.isEmpty()) {
		        user_id = Integer.parseInt(user_idStr);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}
		
		System.out.println(user_id);
		
		RegisterUserModel registerUserModel = new RegisterUserModel (user_name, user_password, user_email, user_number);
		
		int result = dbController.UpdateUser(registerUserModel, user_id);
		
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/pages/loginUser.jsp");
		} else {
			
		}
		
		
		
	}

}
